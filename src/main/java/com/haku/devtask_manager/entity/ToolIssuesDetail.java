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
public class ToolIssuesDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long toolIssuesDetailId;

    private int quantity;
    @ManyToOne
    @JoinColumn(name = "issuesId")
    private Issues issues;
    @ManyToOne
    @JoinColumn(name = "toolId")
    private Tool tool;

}
