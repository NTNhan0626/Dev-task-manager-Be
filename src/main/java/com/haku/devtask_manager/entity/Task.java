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
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    private String taskName;
    private String description;
    private String status;
    private String taskCondition; // It will have 3 values: active, paused, canceled
    private String progress;
    private Date createDate;
    private Date startDate;
    private Date endDate ;
    private Date actualEndDate ;
    private Long managerTaskId; // người quản lí task này có quyền tạo task con cho nó

    @ManyToOne
    @JoinColumn(name = "parentTaskId")
    private Task parentTask;

    @OneToMany(mappedBy = "parentTask",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Task> taskList;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;

    @OneToMany(mappedBy = "task",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<TaskDetail> taskDetails; // quản lí nhân viên trong công việc

    @OneToMany(mappedBy = "task",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<LogWork> logWorks;

    @OneToMany(mappedBy = "task",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Issues> issues;

    @OneToMany(mappedBy = "task",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<TaskEvaluation> taskEvaluations;

    
}
