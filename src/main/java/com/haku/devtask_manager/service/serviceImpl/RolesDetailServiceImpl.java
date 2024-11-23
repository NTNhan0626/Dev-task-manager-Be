package com.haku.devtask_manager.service.serviceImpl;

import com.haku.devtask_manager.entity.Account;
import com.haku.devtask_manager.entity.Roles;
import com.haku.devtask_manager.entity.RolesDetail;
import com.haku.devtask_manager.exception.CustomRuntimeException;
import com.haku.devtask_manager.exception.ExceptionCode;
import com.haku.devtask_manager.mapper.AccountMapper;
import com.haku.devtask_manager.mapper.RolesDetailMapper;
import com.haku.devtask_manager.payload.entityrequest.RolesDetailRequest;
import com.haku.devtask_manager.payload.entityresponse.RolesDetailResponse;
import com.haku.devtask_manager.repository.AccountRepo;
import com.haku.devtask_manager.repository.RolesDetailRepo;
import com.haku.devtask_manager.repository.RolesRepo;
import com.haku.devtask_manager.service.RolesDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class RolesDetailServiceImpl implements RolesDetailService {

    private final RolesDetailRepo rolesDetailRepo;
    private final AccountRepo accountRepo;
    private final RolesRepo rolesRepo;

    private final RolesDetailMapper rolesDetailMapper;
    private final AccountMapper accountMapper;
    @Override
    public RolesDetailResponse getRolesDetailById(Long rolesDetailId) {
        return null;
    }

    @Override
    public RolesDetailResponse addRolesDetail(RolesDetailRequest rolesDetailRequest, Long accountId, Long rolesId) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new CustomRuntimeException(ExceptionCode.USER_NOT_FOUND.getCode(),ExceptionCode.USER_NOT_FOUND.getMessage()));
        Roles roles = rolesRepo.findById(rolesId)
                .orElseThrow(() -> new CustomRuntimeException(ExceptionCode.USER_NOT_FOUND.getCode(),ExceptionCode.USER_NOT_FOUND.getMessage()));

        boolean checker = rolesDetailRepo.existsByAccount_AccountIdAndRoles_RolesId(accountId,rolesId);
        if(checker){
            throw new CustomRuntimeException(ExceptionCode.ROLESDETAIL_EXISTS.getCode(),ExceptionCode.ROLESDETAIL_EXISTS.getMessage());
        }
        RolesDetail rolesDetail = rolesDetailMapper.toRolesDetail(rolesDetailRequest);
        rolesDetail.setAccount(account);
        rolesDetail.setRoles(roles);

        log.info("RolesDetailRequest: {}", rolesDetailRequest);

        rolesDetailRepo.save(rolesDetail);
        return rolesDetailMapper.toRolesDetailResponse(rolesDetail);
    }


    @Override
    public RolesDetailResponse updateRolesDetail(RolesDetailRequest rolesDetailRequest, Long accountId,Long rolesId) {
        RolesDetail rolesDetail = rolesDetailRepo.findOneByAccount_AccountIdAndRoles_RolesId(accountId,rolesId);
        if(rolesDetail == null){
            throw new CustomRuntimeException(ExceptionCode.ROLESDETAIL_NOTEXISTS.getCode(),ExceptionCode.ROLESDETAIL_NOTEXISTS.getMessage());
        }

        rolesDetail.setManager(rolesDetailRequest.isManager());
        rolesDetail.setStaff(rolesDetailRequest.isStaff());
        rolesDetail.setGuest(rolesDetailRequest.isGuest());
        rolesDetail.setReader(rolesDetailRequest.isReader());
        rolesDetail.setWriter(rolesDetailRequest.isWriter());
        rolesDetail.setCreater(rolesDetailRequest.isCreater());
        rolesDetail.setDeleter(rolesDetailRequest.isDeleter());

        rolesDetailRepo.save(rolesDetail);
        return rolesDetailMapper.toRolesDetailResponse(rolesDetail);
    }

    @Override
    public void deleteRolesDetail(Long rolesDetailId) {

    }

    @Override
    public List<RolesDetailResponse> getRolesDetailByRolesId(Long rolesId) {
        Roles roles = rolesRepo.findById(rolesId).orElseThrow(
                () -> new CustomRuntimeException(ExceptionCode.USER_NOT_FOUND.getCode(),ExceptionCode.USER_NOT_FOUND.getMessage())
        );
//        List<RolesDetailResponse> rolesDetailResponses = rolesDetailMapper.toRolesDetailResponseList(rolesDetailRepo.findAllRolesDetailByRoles(roles));
        List<RolesDetailResponse> rolesDetailResponses = new ArrayList<>();

        List<RolesDetail> rolesDetailList = rolesDetailRepo.findAllRolesDetailByRoles(roles);
        rolesDetailList.forEach(rolesDetail -> {
            RolesDetailResponse rolesDetailResponse = rolesDetailMapper.toRolesDetailResponse(rolesDetail);
            rolesDetailResponse.setAccountResponse(accountMapper.toAccountResponse(rolesDetail.getAccount()));
            rolesDetailResponses.add(rolesDetailResponse);
        });
        return rolesDetailResponses;
    }

    @Override
    public List<RolesDetailResponse> createRolesDetailsByDepartmentNameAndAccounts(String departmentName, List<Long> accountIds) {
        // because RolesName = departmentName when adding employees to a department, add rolesdetails at the same time
        Roles roles = rolesRepo.findOneByRolesName(departmentName);
        List<RolesDetail> rolesDetailList = new ArrayList<>();
        List<Account> accounts = accountRepo.findAllById(accountIds);
        accounts.forEach(account -> {
            RolesDetail rolesDetail = new RolesDetail();
            rolesDetail.setRoles(roles);
            rolesDetail.setAccount(account);
            rolesDetail.setManager(false);
            rolesDetail.setStaff(false);
            rolesDetail.setGuest(false);
            rolesDetail.setReader(false);
            rolesDetail.setWriter(false);
            rolesDetail.setCreater(false);
            rolesDetail.setDeleter(false);
            rolesDetailList.add(rolesDetail);
            rolesDetailRepo.save(rolesDetail);
        });
        return rolesDetailList.stream()
                .map(rolesDetail -> {
                    RolesDetailResponse rolesDetailResponse = rolesDetailMapper.toRolesDetailResponse(rolesDetail);
                    rolesDetailResponse.setAccountResponse(accountMapper.toAccountResponse(rolesDetail.getAccount()));
                    return rolesDetailResponse;
                } )
                .collect(Collectors.toList());
    }

    @Override
    public List<RolesDetailResponse> deleteRolesDetailsByDepartmentNameAndAccounts(String departmentName, List<Long> accountIds) {
        Roles roles = rolesRepo.findOneByRolesName(departmentName);
        List<Account> accounts = accountRepo.findAllById(accountIds);

        List<RolesDetail> rolesDetailListWasDeleted = rolesDetailRepo.findAllByAccountInAndRoles(accounts,roles);
        accounts.forEach(account -> {
            rolesDetailRepo.deleteByAccountAndRoles(account,roles);
        });
        return rolesDetailListWasDeleted.stream()
                .map(rolesDetail -> {
                    RolesDetailResponse rolesDetailResponse = rolesDetailMapper.toRolesDetailResponse(rolesDetail);
                    rolesDetailResponse.setAccountResponse(accountMapper.toAccountResponse(rolesDetail.getAccount()));
                    return rolesDetailResponse;
                })
                .collect(Collectors.toList());
    }
}
