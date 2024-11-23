package com.haku.devtask_manager.payload.entityrequest;

import com.haku.devtask_manager.entity.DepartmentDetail;
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

public class DepartmentRequest {

    private String departmentName;
    private Date createdDate;
    private String status;

}
