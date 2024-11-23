package com.haku.devtask_manager.payload.entityresponse;

import com.haku.devtask_manager.entity.Account;
import com.haku.devtask_manager.entity.RolesDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RolesResponse {
    private Long rolesId;
    private String rolesName;

    private List<AccountResponse> accountResponseList;

}
