package com.haku.devtask_manager.payload.entityresponse;

import com.haku.devtask_manager.entity.Issues;
import com.haku.devtask_manager.entity.Tool;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToolIssuesDetailResponse {

    private Long toolIssuesDetailId;

    private int quantity;

    private String issuesName;
    private Long issuesId;

    private String toolName;
    private Long toolId;

}
