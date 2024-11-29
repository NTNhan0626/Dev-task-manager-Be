package com.haku.devtask_manager.payload.entityresponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProjectDepartmentDetailResponse {

    private String status;
    private String departmentName;
    private Long departmentId;

}
