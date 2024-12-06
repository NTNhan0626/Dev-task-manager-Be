package com.haku.devtask_manager.payload.entityrequest;

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
public class TaskEvaluationRequest {

    private Long taskEvaluationId;
    private String quantity; // sẽ có 4 giá  trị tệ, bình thường, tốt, rất tốt
    private String feedback; // nhận xét

}
