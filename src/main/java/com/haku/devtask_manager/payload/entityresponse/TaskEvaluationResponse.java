package com.haku.devtask_manager.payload.entityresponse;

import com.haku.devtask_manager.entity.Account;
import com.haku.devtask_manager.entity.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskEvaluationResponse {

    private Long taskEvaluationId;
    private String quantity; // sẽ có 4 giá  trị tệ, bình thường, tốt, rất tốt
    private String feedback; // nhận xét


    private Long taskId;
    private String taskName;

    private Long accountId;
    private String accountName;
}