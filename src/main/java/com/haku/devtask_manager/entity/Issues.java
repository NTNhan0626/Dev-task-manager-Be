package com.haku.devtask_manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Issues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issuesId;

    private String issueName; // Tên vấn đề
    private String description; // Mô tả chi tiết
    private String status; // Trạng thái (Pending, , .)
    private String type; // loại phát sinh (có thể là yc thêm công cụ hay gì khác)
    private Date reportedDate; // Ngày báo cáo
    private Date resolvedDate; // Ngày xử lý xong (có thể null nếu chưa xử lý)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taskId", nullable = false)
    private Task task; // Liên kết với dự án

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountrpId", nullable = false)
    private Account accountrp; // Nhân viên báo cáo vấn đề

    @OneToMany(mappedBy = "issues",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<ToolIssuesDetail> toolIssuesDetailList;



}
