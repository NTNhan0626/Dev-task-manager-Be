package com.haku.devtask_manager.service.serviceImpl;


import com.haku.devtask_manager.entity.*;
import com.haku.devtask_manager.exception.CustomRuntimeException;
import com.haku.devtask_manager.exception.ExceptionCode;
import com.haku.devtask_manager.mapper.AccountMapper;
import com.haku.devtask_manager.payload.entityrequest.AccountRequest;
import com.haku.devtask_manager.payload.entityresponse.*;
import com.haku.devtask_manager.repository.AccountRepo;
import com.haku.devtask_manager.repository.DepartmentDetailRepo;
import com.haku.devtask_manager.repository.RolesDetailRepo;
import com.haku.devtask_manager.repository.RolesRepo;
import com.haku.devtask_manager.service.AccountService;
import com.haku.devtask_manager.service.AuthenticationService;
import com.haku.devtask_manager.service.DegreeDetailService;
import com.haku.devtask_manager.service.SpecializationDetailService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepo accountRepo;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;
    private final DegreeDetailService degreeDetailService;
    private final SpecializationDetailService specializationDetailService;
    private final DepartmentDetailRepo departmentDetailRepo;
    private final RolesDetailRepo rolesDetailRepo;
    private final RolesRepo rolesRepo;

    @Override
    public List<AccountResponse> getAllAccount() {
        List<AccountResponse> accountResponseList = accountMapper.toAccountResponseList(accountRepo.findAll());
        accountResponseList.forEach(accountResponse ->{

            Account account = accountRepo.findOneByAccountId(accountResponse.getAccountId());
            if(account.getInformation() != null){
                accountResponse.setInformationResponse(InformationResponse.builder()
                        .firstName(account.getInformation().getFirstName())
                        .lastName(account.getInformation().getLastName())
                        .Gender(account.getInformation().isGender())
                        .phoneNumber(account.getInformation().getPhoneNumber())
                        .dateOfBirth(account.getInformation().getDateOfBirth())
                        .address(account.getInformation().getAddress())

                        .build());
            }
            if (!account.getDegreeDetails().isEmpty()){
                List<DegreeDetailResponse> degreeDetailResponses = degreeDetailService.getDegreeDetailByAccountId(account.getAccountId());
                accountResponse.setDetailResponses(degreeDetailResponses.stream().filter(degreeDetailResponse -> degreeDetailResponse.getStatus().equals("Đã duyệt")).collect(Collectors.toList()));
            }
            if (!account.getSpecializationDetails().isEmpty()){
                List<SpecializationDetailResponse> specializationDetailResponses = specializationDetailService.getSpecializationDetailByAccountId(account.getAccountId());
                accountResponse.setSpecializationDetailResponses(specializationDetailResponses.stream().filter(specializationDetailResponse -> specializationDetailResponse.getStatus().equals("Đã duyệt")).collect(Collectors.toList()));
            }

        } );
        return accountResponseList;
    }

    @Override
    public AccountResponse getAccountById(Long accountId) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new CustomRuntimeException(ExceptionCode.USER_NOT_FOUND.getCode(), ExceptionCode.USER_NOT_FOUND.getMessage()));
        return accountMapper.toAccountResponse(account);
    }




    @Override
    public AccountResponse addAccount(AccountRequest accountRequest) {
        boolean checker = accountRepo.existsByUsername(accountRequest.getUsername());
        if(checker) {
            throw new CustomRuntimeException(ExceptionCode.USER_EXISTS.getCode(),ExceptionCode.USER_EXISTS.getMessage());
        }
        Account account = accountMapper.toAccount(accountRequest);
        account.setPassword(passwordEncoder.encode(accountRequest.getPassword()));
        accountRepo.save(account);

        return accountMapper.toAccountResponse(account);
    }

    @Override
    public AccountResponse updateAccount(AccountRequest accountRequest, Long accountId) {
        Optional<Account> accountOptional = accountRepo.findById(accountId);
        if (accountOptional.isEmpty()) {
            throw new CustomRuntimeException(ExceptionCode.USER_NOT_FOUND.getCode(), ExceptionCode.USER_NOT_FOUND.getMessage());
        }
        Account account = accountOptional.get();
        if (accountRequest.getEmail()!=null) account.setEmail(accountRequest.getEmail());

        if (accountRequest.getStatus()!=null) account.setStatus(accountRequest.getStatus());



        accountRepo.save(account);

        return accountMapper.toAccountResponse(account);
    }
    @Override
    public UserAuthResponse getToken(String username, String password) throws JOSEException, ParseException {
        AtomicReference<String> typeRoles = new AtomicReference<>("");
        String departmentName = "";
        Long departmentId = null;
        Account account = accountRepo.findOneByUsername(username);
        if(account == null){
            throw new CustomRuntimeException(ExceptionCode.USER_NOT_FOUND.getCode(),ExceptionCode.USER_NOT_FOUND.getMessage());
        }
        if (account.getStatus().equals("Paused")){
            throw new CustomRuntimeException(ExceptionCode.USER_HAS_BEEN_BLOCKED.getCode(),ExceptionCode.USER_HAS_BEEN_BLOCKED.getMessage());
        }

        // tìm phòng ban hiện tại của nhana viên
        boolean b = departmentDetailRepo.existsByAccountAndTimeOutIsNull(account);
        DepartmentDetail departmentDetail = new DepartmentDetail();
        if(b) {
            departmentDetail = departmentDetailRepo.findOneByAccountAndTimeOutIsNull(account);
            //nếu nhân viên có phòng thì xem nhân vên đó có chức vụ gì trong phòng
            Roles roles = rolesRepo.findOneByRolesName(departmentDetail.getDepartment().getDepartmentName()); // vì tên phòng và tên quyền bằng nhay
            RolesDetail rolesDetail = rolesDetailRepo.findOneByAccount_AccountIdAndRoles_RolesId(account.getAccountId(),roles.getRolesId());
            if (rolesDetail.isStaff()){
                typeRoles.set("staff");
            }
            if (rolesDetail.isManager()){
                typeRoles.set("manager");
            }
            if (rolesDetail.isGuest()){
                typeRoles.set("guest");
            }
            departmentName = departmentDetail.getDepartment().getDepartmentName();
            departmentId = departmentDetail.getDepartment().getDepartmentId();
        }else{
            List<RolesDetail> rolesDetailList = account.getRolesDetailList();
            rolesDetailList.forEach(rolesDetail -> {
                if (rolesDetail.getRoles().getRolesName().equals("leadmanager")){
                    typeRoles.set("leadmanager");
                }
            });
            departmentName = "";
        }

        boolean authenticated = passwordEncoder.matches(password,account.getPassword());
        if(!authenticated){
            throw new CustomRuntimeException(ExceptionCode.INVALID_REQUEST.getCode(),ExceptionCode.INVALID_REQUEST.getMessage());
        }
        String token = authenticationService.generateToken(account);
        //tạo kí xác thực
        JWSVerifier verifier = new MACVerifier("jk6lzqp2tghGPcTCCODK5Jql2qpYmKcMnvBzgg43KccOSusywDLnTgkms83lbsWm".getBytes());
        //giải token
        SignedJWT signedJWT = SignedJWT.parse(token);
        //xác thực chữ kí nhưng không dùng vì token mới được sinh ra
        //boolean verified = signedJWT.verify(verifier);
        // lấy các roles của tài khoản
        // Log xem giá trị của claim "scope" trong token là gì
        String scopeClaim = signedJWT.getJWTClaimsSet().getStringClaim("scope");
        log.info("Scope claim: {}", scopeClaim);
        List<String> scopeList = new ArrayList<>();
        if (scopeClaim != null) {
            scopeList = Arrays.asList(scopeClaim.split(" "));
        }
//
        return UserAuthResponse.builder()
                .token(token)
                .userName(account.getUsername())
                .accountId(account.getAccountId())
                .roles(scopeList)
                .departmentName(departmentName)
                .departmentId(departmentId)
                .typeRoles(typeRoles.get())
                .build();
    }

    @Override
    public List<AccountResponse> findAllByDepartmentDetailListIsNull() {
        return accountMapper.toAccountResponseList(accountRepo.findAllByDepartmentDetailListIsNull());
    }

    @Override
    public AccountResponse deleteAccount(Long accountId) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new CustomRuntimeException(ExceptionCode.USER_NOT_FOUND.getCode(),ExceptionCode.USER_NOT_FOUND.getMessage()));
        accountRepo.delete(account);

        return accountMapper.toAccountResponse(account);
    }

    @Override
    public boolean changePassword(String token, String newPassword, String oldPassword) throws ParseException {
        // Giải mã token
        SignedJWT signedJWT = SignedJWT.parse(token);
        String userName = signedJWT.getJWTClaimsSet().getSubject();

        // Tìm tài khoản người dùng
        Account account = accountRepo.findOneByUsername(userName);
        if (account == null) {
            throw new CustomRuntimeException(ExceptionCode.USER_NOT_FOUND.getCode(), ExceptionCode.USER_NOT_FOUND.getMessage());
        }

        // Kiểm tra mật khẩu cũ có khớp không
        boolean authenticated = passwordEncoder.matches(oldPassword, account.getPassword());
        if (!authenticated) {
            throw new CustomRuntimeException(ExceptionCode.INVALID_REQUEST.getCode(), ExceptionCode.INVALID_REQUEST.getMessage());
        }

        // Cập nhật mật khẩu mới
        account.setPassword(passwordEncoder.encode(newPassword));
        // Lưu tài khoản sau khi thay đổi mật khẩu
        accountRepo.save(account);

        // Trả về true nếu thành công
        return true;
    }

}
