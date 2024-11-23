package com.haku.devtask_manager.service.serviceImpl;

import com.haku.devtask_manager.entity.Account;
import com.haku.devtask_manager.entity.Roles;
import com.haku.devtask_manager.entity.RolesDetail;
import com.haku.devtask_manager.exception.CustomRuntimeException;
import com.haku.devtask_manager.exception.ExceptionCode;
import com.haku.devtask_manager.mapper.AccountMapper;
import com.haku.devtask_manager.payload.entityrequest.RolesRequest;
import com.haku.devtask_manager.payload.entityresponse.RolesResponse;
import com.haku.devtask_manager.repository.AccountRepo;
import com.haku.devtask_manager.repository.RolesDetailRepo;
import com.haku.devtask_manager.repository.RolesRepo;
import com.haku.devtask_manager.service.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService {
    private final RolesRepo rolesRepo;
    private final AccountRepo accountRepo;
    private final AccountMapper accountMapper;
    private final RolesDetailRepo rolesDetailRepo;

    @Override
    public List<RolesResponse> getRoles() {
        List<Roles> rolesList = rolesRepo.findAll();

//        Roles roles = rolesRepo.findById(1L).orElseThrow();
//        List<RolesDetail> list = roles.getRolesDetailList();
//        list.forEach(rolesDetail -> rolesDetail.getAccount());
        // Kiểm tra nếu rolesList là null hoặc trống
        if (rolesList.isEmpty()) {
            return Collections.emptyList(); // Trả về danh sách trống
        }


        // Sử dụng Stream API để chuyển đổi rolesList thành rolesResponses
        return rolesList.stream()
                .map(role -> RolesResponse.builder()
                        .rolesId(role.getRolesId())
                        .rolesName(role.getRolesName())

                        .accountResponseList(accountMapper.toAccountResponseList(rolesDetailRepo.findAllRolesDetailByRoles(role)
                                .stream()
                                .map(RolesDetail::getAccount)
                                .collect(Collectors.toList())))
                        .build())
                .collect(Collectors.toList());
    }


    @Override
    public RolesResponse getRolesById(Long rolesId) {
        Roles roles = rolesRepo.findById(rolesId)
                .orElseThrow(() -> new CustomRuntimeException(ExceptionCode.USER_NOT_FOUND.getCode(),ExceptionCode.USER_NOT_FOUND.getMessage()));

        return RolesResponse.builder()
                .rolesName(roles.getRolesName())

                .build();
    }

    @Override
    public RolesResponse addRoles(RolesRequest rolesRequest) {
        boolean checker = rolesRepo.existsByRolesName(rolesRequest.getRolesName());
        if(checker){
            throw new CustomRuntimeException(ExceptionCode.USER_EXISTS.getCode(),ExceptionCode.USER_EXISTS.getMessage());
        }

        Roles roles = new Roles();
        roles.setRolesName(rolesRequest.getRolesName());
        rolesRepo.save(roles);

        return RolesResponse.builder()
                .rolesName(roles.getRolesName())

                .build();
    }

    @Override
    public RolesResponse updateRoles(RolesRequest rolesRequest, Long rolesId) {
        Roles roles = rolesRepo.findById(rolesId)
                .orElseThrow(() -> new CustomRuntimeException(ExceptionCode.USER_NOT_FOUND.getCode(),ExceptionCode.USER_NOT_FOUND.getMessage()));
        roles.setRolesName(rolesRequest.getRolesName());
        return RolesResponse.builder()
                .rolesName(roles.getRolesName())
                .build();
    }

    @Override
    public void deleteRoles(Long rolesId) {

    }
}
