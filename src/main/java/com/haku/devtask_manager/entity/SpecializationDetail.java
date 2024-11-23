package com.haku.devtask_manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpecializationDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long specializationDetailId;

    private String level;                     // Cấp độ chuyên môn (VD: Junior, Senior, Expert)
    private Date startDate;                   // Ngày bắt đầu có chuyên môn
    private String description;
    private String status;

    @ManyToOne()
    @JoinColumn(name = "specializationId")
    private Specialization specialization;    // Tham chiếu đến chuyên môn

    @ManyToOne()
    @JoinColumn(name = "accountId")
    private Account account;                // Tham chiếu đến nhân viên

}
