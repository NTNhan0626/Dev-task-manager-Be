package com.haku.devtask_manager.service;

import com.haku.devtask_manager.payload.entityrequest.AccountRequest;
import com.haku.devtask_manager.payload.entityrequest.RolesRequest;
import com.haku.devtask_manager.payload.entityresponse.AccountResponse;
import com.haku.devtask_manager.payload.entityresponse.RolesResponse;

import java.util.List;


public interface RolesService {
    List<RolesResponse> getRoles();
    RolesResponse getRolesById(Long rolesId);
    RolesResponse addRoles(RolesRequest rolesRequest);
    RolesResponse updateRoles(RolesRequest rolesRequest,Long rolesId);
    void deleteRoles(Long rolesId);

}
