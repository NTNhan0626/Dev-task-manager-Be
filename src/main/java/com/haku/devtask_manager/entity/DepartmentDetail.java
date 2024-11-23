package com.haku.devtask_manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class DepartmentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Date timeIn;
    private Date timeOut;
    private String status;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "accountId")
    private Account account;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "departmentId")
    private Department department;
}
