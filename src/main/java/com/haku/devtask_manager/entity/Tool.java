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
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long toolId;

    private String toolName;
    private String description;
    private String toolType; //Loại công cụ (ví dụ: máy móc, phần mềm, thiết bị)
    private int quantityAvailable;
    private Date createdDate;
    private Date updatedDate;

    @OneToMany(mappedBy = "tool",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<ToolProjectDetail> projectToolDetails ;
    @OneToMany(mappedBy = "tool",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<ToolIssuesDetail> toolIssuesDetailList ;


}
