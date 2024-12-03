package com.haku.devtask_manager.payload.entityrequest;

import com.haku.devtask_manager.entity.ToolProjectDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToolRequest {



    private String toolName;
    private String description;
    private String toolType; //Loại công cụ (ví dụ: máy móc, phần mềm, thiết bị)
    private int quantityAvailable;
    private Date createdDate;
    private Date updatedDate;

}
