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
public class ToolProjectDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long toolProjectDetailId; // Id cho bảng chi tiết công cụ dự án

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "toolId")
    private Tool tool; // Mối quan hệ với công cụ

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId")
    private Project project; // Mối quan hệ với dự án
    private int totalQuantity; // tổng số lượng công cụ
    private int quantityUsed; // Số lượng công cụ đã sử dụng trong dự án
    private Date allocatedDate; // Ngày phân bổ công cụ cho dự án
    private Date returnDate;// ngày trả công cụ lại
    private String status; // Trạng thái công cụ (ví dụ: Đang sử dụng, Đã trả lại, Hỏng, ...)

}
