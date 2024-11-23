package com.haku.devtask_manager.payload.entityresponse;

import com.haku.devtask_manager.entity.Account;
import com.haku.devtask_manager.entity.Roles;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RolesDetailResponse {

    private boolean manager;
    private boolean staff;
    private boolean guest;
    private boolean reader;
    private boolean writer;
    private boolean creater;
    private boolean deleter;
    private AccountResponse accountResponse;

}
