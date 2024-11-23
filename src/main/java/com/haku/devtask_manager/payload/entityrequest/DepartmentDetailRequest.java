package com.haku.devtask_manager.payload.entityrequest;

import com.haku.devtask_manager.entity.Account;
import com.haku.devtask_manager.entity.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class DepartmentDetailRequest {

    private Date timeIn;
    private Account account;
    private Department department;
    private String status;
}
