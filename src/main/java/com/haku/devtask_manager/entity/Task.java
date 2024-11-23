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
    private String progress;
    private Date startDate;
    private Date endDate ;
    private Date actualEndDate ;

    @ManyToOne
    @JoinColumn(name = "parentTaskId")
    private Task parentTask;

    @OneToMany(mappedBy = "parentTask",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Task> taskList;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;





}
