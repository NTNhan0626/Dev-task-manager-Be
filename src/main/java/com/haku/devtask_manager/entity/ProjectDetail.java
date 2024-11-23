package com.haku.devtask_manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectDetailId;

    private String status;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;
}
