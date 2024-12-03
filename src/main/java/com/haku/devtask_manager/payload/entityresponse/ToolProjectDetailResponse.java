package com.haku.devtask_manager.payload.entityresponse;

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
public class ToolProjectDetailResponse {


    private Long toolProjectDetailId; // Id cho bảng chi tiết công cụ dự án


    private String toolName; // Mối quan hệ với công cụ
    private Long toolId;


    private String projectName; // Mối quan hệ với dự án
    private Long projectId; // Mối quan hệ với dự án

    private int totalQuantity; // số lượng công cụ yêu cầu
    private int quantityUsed; // Số lượng công cụ đã sử dụng trong dự án
    private Date allocatedDate; // Ngày phân bổ công cụ cho dự án
    private Date returnDate;// ngày trả công cụ lại
    private String status; // Trạng thái công cụ (ví dụ: Đang sử dụng, Đã trả lại, Hỏng, ...)

}
