package com.haku.devtask_manager.payload.entityrequest;

import com.haku.devtask_manager.entity.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogWorkRequest {


    private String logworkName; // ngắn gọn hôm nay làm cái gì
    private String description; // mô tả chi tiết xem kàm cái gì
    private Date createDate;
    private String time;
    private String status; // trạng thái logwork xem có được duyệt hay không

    private Long taskId;
    private String taskName;

}
