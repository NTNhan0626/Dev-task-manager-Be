package com.haku.devtask_manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    private String projectName;
    private String description;
    private boolean projectType; // true là dự án phòng ban false là liên phòng ban
    private Date createdDate;
    private Date startDate;
    private Date endDate;
    private Date actualEndDate;
    private String status;

    private double progress;
    private Long projectManagerId; // id người người ohuj trách dự án đối với dự án phòng ban thì ng phụ trách là quản lí phòng ban còn liên phòng ban thì được chỉ định
    private Long createrId;// dùng cho dự án liên phòng ban để phân biệt giữa các lead manager
    private String projectCondition; // It will have 3 values: active, paused, canceled



    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<ProjectDetail> projectDetails;

    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<ProjectDepartmentDetail> projectDepartmentDetails;

    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Task> taskList;
    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<ToolProjectDetail> toolProjectDetails;

}
