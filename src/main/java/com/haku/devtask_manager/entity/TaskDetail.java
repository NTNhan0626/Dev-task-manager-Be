package com.haku.devtask_manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskDetailId;

    private String status; // kiểm tra xem nhân viên còn làm hay không

    @ManyToOne
    @JoinColumn(name = "taskId")
    private Task task;
    private Date joinDate;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;


}
