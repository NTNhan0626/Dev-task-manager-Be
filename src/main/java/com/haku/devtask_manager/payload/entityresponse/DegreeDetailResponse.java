package com.haku.devtask_manager.payload.entityresponse;

import com.haku.devtask_manager.entity.Account;
import com.haku.devtask_manager.entity.Degree;
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


public class DegreeDetailResponse {

    private Long id;
    private Date issueDate;           // Ngày cấp bằng
    private Date expiryDate;          // Ngày hết hạn, áp dụng cho các bằng cấp có thời hạn
    private String institution;     // Tên trường cấp bằng (Ví dụ: "Đại học Bách Khoa")
    private String level;           // Cấp độ (Ví dụ: "Cử nhân", "Thạc sĩ")
    private int duration;
    private String status;

    private String degreeName;            // Tham chiếu đến loại bằng cấp (TOEIC, IELTS, etc.)
    private Long degreeId;
    private String accountName;        // Tham chiếu đến nhân viên
    private String major;
}

