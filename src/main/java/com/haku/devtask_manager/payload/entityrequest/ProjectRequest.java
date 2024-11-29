package com.haku.devtask_manager.payload.entityrequest;

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
public class ProjectRequest {
    private String projectName;
    private String description;
    private boolean projectType;
    private Date createdDate;
    private Date startDate;
    private Date endDate;
    private Date actualEndDate;
    private String status;
    private String projectCondition; // It will have 3 values: active, paused, canceled
    private double progress;
    private Long projectManagerId;

    private List<Long> departmentIds;
}
