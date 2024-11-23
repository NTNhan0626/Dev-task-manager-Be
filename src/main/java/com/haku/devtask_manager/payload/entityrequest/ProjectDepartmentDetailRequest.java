package com.haku.devtask_manager.payload.entityrequest;

import com.haku.devtask_manager.entity.Department;
import com.haku.devtask_manager.entity.Project;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProjectDepartmentDetailRequest {

    private String status;

}
