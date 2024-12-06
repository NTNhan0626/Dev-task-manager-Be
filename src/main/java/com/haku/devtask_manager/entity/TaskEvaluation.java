package com.haku.devtask_manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskEvaluationId;
    private String quantity; // sẽ có 4 giá  trị tệ, bình thường, tốt, rất tốt
    private String feedback; // nhận xét

    @ManyToOne
    @JoinColumn(name = "taskId")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;
}
