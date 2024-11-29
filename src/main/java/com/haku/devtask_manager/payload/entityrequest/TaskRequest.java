package com.haku.devtask_manager.payload.entityrequest;

import com.haku.devtask_manager.entity.Project;
import com.haku.devtask_manager.entity.TaskDetail;
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
public class TaskRequest {

    private String taskName;
    private String description;
    private String status;
    private String taskCondition; // It will have 3 values: active, paused, canceled
    private String progress;
    private Date createDate;
    private Date startDate;
    private Date endDate ;
    private Date actualEndDate ;
    private Long managerTaskId; // người quản lí task này có quyền tạo task con cho nó

    private Long taskParentId;// trường hợp công việc gốc không thể gửi id công việc cha = null qua url

//    private Long parentTaskId;
//
//    private Long projectId;


    
}
