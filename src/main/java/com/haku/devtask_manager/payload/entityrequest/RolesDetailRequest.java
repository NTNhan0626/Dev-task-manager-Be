package com.haku.devtask_manager.payload.entityrequest;

import com.haku.devtask_manager.entity.Account;
import com.haku.devtask_manager.entity.Roles;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class RolesDetailRequest {

    private boolean manager;
    private boolean staff;
    private boolean guest;
    private boolean reader;
    private boolean writer;
    private boolean creater;
    private boolean deleter;


}
