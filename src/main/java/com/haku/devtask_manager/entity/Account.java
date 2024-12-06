package com.haku.devtask_manager.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String username;
    private String password;
    private String email;
    private String status;
    private Date dateCreate;

    @OneToOne(mappedBy = "account",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Information information;

    @OneToMany(mappedBy = "account")
    private List<RolesDetail> rolesDetailList;

    @OneToMany(mappedBy = "account" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<DepartmentDetail> departmentDetailList;

    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<DegreeDetail> degreeDetails;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<SpecializationDetail> specializationDetails;

    @OneToMany(mappedBy = "account")
    private List<Category> categoryList;

    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<ProjectDetail> projectDetails;
    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<TaskDetail> taskDetails;

    @OneToMany(mappedBy = "accountrp", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Issues> issues;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<TaskEvaluation> taskEvaluations;

}
