package com.haku.devtask_manager.payload.entityrequest;

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
public class ToolIssuesDetailRequest {


    private int quantity;
    private Long toolId;
    private Long issuesId;

}
