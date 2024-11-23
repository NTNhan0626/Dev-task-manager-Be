package com.haku.devtask_manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class DegreeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "degreeId")
    private Degree degree;            // Tham chiếu đến loại bằng cấp (TOEIC, IELTS, etc.)

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;        // Tham chiếu đến nhân viên

    private Date issueDate;           // Ngày cấp bằng
    private Date expiryDate;          // Ngày hết hạn, áp dụng cho các bằng cấp có thời hạn
    private String institution;     // Tên trường cấp bằng (Ví dụ: "Đại học Bách Khoa")
    private String level;           // Cấp độ (Ví dụ: "Cử nhân", "Thạc sĩ")
    private int duration;           // Thời gian học (năm)
    private String status;
}

