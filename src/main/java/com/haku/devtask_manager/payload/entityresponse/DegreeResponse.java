package com.haku.devtask_manager.payload.entityresponse;

import com.haku.devtask_manager.entity.DegreeDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DegreeResponse {
    private Long degreeId;         // ID của bằng cấp

    private String degreeName;      // Tên bằng cấp (Ví dụ: "Bachelor of Science")
    private String major;           // Ngành học (Ví dụ: "Computer Science")

    private List<DegreeDetailResponse> degreeDetailResponses;  // Danh sách chi tiết bằng cấp liên kết
}
