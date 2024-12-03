package com.haku.devtask_manager.payload.entityresponse;

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
public class TaskResponse {
    private Long taskId;

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

    private Long projectManagerId; // id của người quản lí dự án sử dụng đối với dự án liên phòng ban
    private Long parentTaskId;
    private String parentTaskName;
    private String parentTaskStatus; // trạng thái của công việc cha
    private Long parentManagerTaskId; // id của người quản lí task cha
    private Long projectCreaterId; // id của người tạo dự án để phân biệt giưuax các tài khoanr có quyền leadmanager
    private List<TaskResponse> taskResponseList; // các task con


    private String projectName;
    private Long projectId;


    private List<TaskDetailResponse> taskDetailResponses; // ds nhân viên trong công việc

}
