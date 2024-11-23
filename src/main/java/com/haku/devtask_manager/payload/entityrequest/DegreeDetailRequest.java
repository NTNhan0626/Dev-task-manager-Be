package com.haku.devtask_manager.payload.entityrequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class DegreeDetailRequest {
    private Long accountId;
    private Long degreeId;

    private Date issueDate;           // Ngày cấp bằng
    private Date expiryDate;          // Ngày hết hạn, áp dụng cho các bằng cấp có thời hạn
    private String institution;     // Tên trường cấp bằng (Ví dụ: "Đại học Bách Khoa")
    private String level;           // Cấp độ (Ví dụ: "Cử nhân", "Thạc sĩ")
    private int duration;           // Thời gian học (năm)
    private String status;
}

