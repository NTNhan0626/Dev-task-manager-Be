package com.haku.devtask_manager.payload.entityresponse;

import com.haku.devtask_manager.entity.Account;
import com.haku.devtask_manager.entity.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDetailResponse {

    private Long taskDetailId;

    private String status; // kiểm tra xem nhân viên còn làm hay không


    private String taskName;
    private Date joinDate;

    private String accountName;
    private Long accountId;


}
