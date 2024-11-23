package com.haku.devtask_manager.payload.entityrequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


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
    private double progress;
    private Long projectManagerId;
}
