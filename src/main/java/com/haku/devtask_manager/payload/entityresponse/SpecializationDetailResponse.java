package com.haku.devtask_manager.payload.entityresponse;

import com.haku.devtask_manager.entity.Account;
import com.haku.devtask_manager.entity.Specialization;
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
public class SpecializationDetailResponse {
    private Long specializationDetailId;
    private String level;                     // Cấp độ chuyên môn (VD: Junior, Senior, Expert)
    private Date startDate;                   // Ngày bắt đầu có chuyên môn
    private String description;
    private String status;

    private String specializationName;

    private Long specializationId;
    private String accountName;

}
