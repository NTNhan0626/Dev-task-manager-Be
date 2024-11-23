package com.haku.devtask_manager.service.serviceImpl;

import com.haku.devtask_manager.entity.*;
import com.haku.devtask_manager.exception.CustomRuntimeException;
import com.haku.devtask_manager.exception.ExceptionCode;
import com.haku.devtask_manager.mapper.AccountMapper;
import com.haku.devtask_manager.payload.entityrequest.DepartmentDetailRequest;
import com.haku.devtask_manager.payload.entityresponse.AccountResponse;
import com.haku.devtask_manager.payload.entityresponse.DepartmentDetailResponse;
import com.haku.devtask_manager.repository.*;
import com.haku.devtask_manager.service.DepartmentDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentDetailServiceImpl implements DepartmentDetailService {
    private final DepartmentDetailRepo departmentDetailRepo;
    private final AccountRepo accountRepo;
    private final AccountMapper accountMapper;
    private final DepartmentRepo departmentRepo;
    private final RolesDetailRepo rolesDetailRepo;
    private final RolesRepo rolesRepo;

    @Override
    public List<DepartmentDetailResponse> getAllDepartmentDetail(Long departmentId) {
        List<DepartmentDetail> departmentDetailList = departmentDetailRepo.findAllByDepartment_DepartmentId(departmentId);

        return departmentDetailList.stream()
                .map(departmentDetail -> {
                    //set position for accountRespone
                    String position = "";
                    AccountResponse accountResponse = accountMapper.toAccountResponse(departmentDetail.getAccount());
                    if(departmentDetail.getTimeOut() != null){
                        position ="";
                    }else{
                        Roles roles = rolesRepo.findOneByRolesName(departmentDetail.getDepartment().getDepartmentName());
                        Long accountID = departmentDetail.getAccount().getAccountId();
                        RolesDetail rolesDetail = rolesDetailRepo.findOneByAccount_AccountIdAndRoles_RolesId(accountID,roles.getRolesId());
                        if(rolesDetail.isManager()){
                            position="manager";
                        }else if(rolesDetail.isStaff()){
                            position="staff";
                        }else{
                            position="guest";
                        }

                    }
                    accountResponse.setPosition(position);
                    return DepartmentDetailResponse.builder()
                            .id(departmentDetail.getId())
                            .timeIn(departmentDetail.getTimeIn())
                            .timeOut(departmentDetail.getTimeOut())
                            .accountResponse(accountResponse)
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDetailResponse getDepartmentDetailById(Long departmentId, Long accountId) {
        return null;
    }

    @Override
    public DepartmentDetailResponse createDepartmentDetail(DepartmentDetailRequest departmentDetailRequest) {
        return null;
    }

    @Override
    public DepartmentDetailResponse updateDepartmentDetail(DepartmentDetailRequest departmentDetailRequest, Long departmentId, Long accountId) {
        return null;
    }

    @Override
    public DepartmentDetailResponse deleteDepartmentDetail(Long departmentId) {
        return null;
    }

    @Override
    public List<DepartmentDetailResponse> createDepartmentDetailsByDepartmentAndAccounts(Long departmentId, List<Long> accountId) {
        Department department = departmentRepo.findById(departmentId)
                .orElseThrow(() -> new CustomRuntimeException(ExceptionCode.DEPARTMENT_NOTEXISTS.getCode(),ExceptionCode.DEPARTMENT_NOTEXISTS.getMessage()));
        List<Account> accounts = accountRepo.findAllById(accountId);
        List<DepartmentDetail> departmentDetails = new ArrayList<>();
        accounts.forEach(account -> {
            DepartmentDetail departmentDetail = new DepartmentDetail();
            Date date = new Date();
            departmentDetail.setDepartment(department);
            departmentDetail.setAccount(account);
            departmentDetail.setTimeIn(date);
            departmentDetails.add(departmentDetail);

            departmentDetailRepo.save(departmentDetail);
        });
        return departmentDetails.stream()
                .map(departmentDetail -> {
                            return DepartmentDetailResponse.builder()
                                    .timeIn(departmentDetail.getTimeIn())
                                    .timeOut(departmentDetail.getTimeOut())
                                    .id(departmentDetail.getId())
                                    .accountResponse(accountMapper.toAccountResponse(departmentDetail.getAccount()))
                                    .build();
                        }
                     )
                .collect(Collectors.toList());
    }

    @Override
    public List<DepartmentDetailResponse> updateDepartmenDetailForTranferStaff(List<Long> departmentDetailId) {
        List<DepartmentDetail> departmentDetails = departmentDetailRepo.findAllById(departmentDetailId);
        departmentDetails.forEach(departmentDetail -> {
            departmentDetail.setTimeOut(new Date());
            departmentDetailRepo.save(departmentDetail);
        });
        return departmentDetails.stream()
                .map(departmentDetail -> {
                    return DepartmentDetailResponse.builder()
                            .id(departmentDetail.getId())
                            .timeIn(departmentDetail.getTimeIn())
                            .timeOut(departmentDetail.getTimeOut())
                            .accountResponse(accountMapper.toAccountResponse(departmentDetail.getAccount()))
                            .build();
                })
                .collect(Collectors.toList());
    }
}
