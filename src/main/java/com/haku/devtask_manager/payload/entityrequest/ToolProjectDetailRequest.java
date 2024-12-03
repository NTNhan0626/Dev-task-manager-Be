package com.haku.devtask_manager.payload.entityrequest;

import com.haku.devtask_manager.entity.Project;
import com.haku.devtask_manager.entity.Tool;
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
public class ToolProjectDetailRequest {

    private Long toolId;
    private Long projectId;

    private int totalQuantity; // tổng số lượng công cụ
    private int quantityUsed; // Số lượng công cụ đã sử dụng trong dự án
    private Date allocatedDate; // Ngày phân bổ công cụ cho dự án
    private Date returnDate;// ngày trả công cụ lại
    private String status; // Trạng thái công cụ (ví dụ: Đang sử dụng, Đã trả lại, Hỏng, ...)

}
