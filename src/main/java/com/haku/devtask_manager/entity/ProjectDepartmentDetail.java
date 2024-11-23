package com.haku.devtask_manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectDepartmentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectDepartmentDetailId;
    private String status;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "departmentd")
    private Department department;

}
