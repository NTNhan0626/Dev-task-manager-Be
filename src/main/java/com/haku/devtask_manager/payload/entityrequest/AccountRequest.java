package com.haku.devtask_manager.payload.entityrequest;

import com.haku.devtask_manager.entity.Degree;
import com.haku.devtask_manager.entity.DepartmentDetail;
import com.haku.devtask_manager.entity.Information;
import com.haku.devtask_manager.entity.RolesDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRequest {
    private String username;
    private String password;
    private String email;
    private String status;
    private Date dateCreate;

    private Information information;
    private List<RolesDetail> rolesDetailList;
    private List<DepartmentDetail> departmentDetailList;
    private List<Degree> degreeList;


}
