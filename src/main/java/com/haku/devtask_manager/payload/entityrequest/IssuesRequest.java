package com.haku.devtask_manager.payload.entityrequest;

import com.haku.devtask_manager.entity.Account;
import com.haku.devtask_manager.entity.Task;
import com.haku.devtask_manager.entity.ToolIssuesDetail;
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
public class IssuesRequest {

    private String issueName; // Tên vấn đề
    private String description; // Mô tả chi tiết
    private String status; // Trạng thái (Pending, , .)
    private String type; // loại phát sinh (có thể là yc thêm công cụ hay gì khác)
    private Date reportedDate; // Ngày báo cáo
    private Date resolvedDate; // Ngày xử lý xong (có thể null nếu chưa xử lý)








}
