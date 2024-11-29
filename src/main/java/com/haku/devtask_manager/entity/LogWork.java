package com.haku.devtask_manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logworkId;

    private String logworkName; // ngắn gọn hôm nay làm cái gì
    private String description; // mô tả chi tiết xem kàm cái gì
    private Date createDate;
    private String time;
    private String status; // trạng thái logwork xem có được duyệt hay không

    @ManyToOne
    @JoinColumn(name = "taskId")
    private Task task;

}
