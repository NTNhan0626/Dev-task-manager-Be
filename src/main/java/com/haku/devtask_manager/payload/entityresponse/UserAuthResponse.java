package com.haku.devtask_manager.payload.entityresponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAuthResponse {
    private String token;
    private String userName;
    private Long accountId;
    private List<String> roles;
    private String departmentName;
    private Long departmentId;
    private String typeRoles;
}
