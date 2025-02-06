package com.haku.devtask_manager.payload.entityresponse;

import com.haku.devtask_manager.entity.DepartmentDetail;
import com.haku.devtask_manager.entity.ProjectDepartmentDetail;
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

public class DepartmentResponse {
    private Long departmentId;
    private String departmentName;
    private String status;
    private Date createdDate;
    private int numberStaff;
    private boolean checkDeleted;

}
