package com.haku.devtask_manager.entity;

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

@Entity
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long degreeId;         // ID của bằng cấp

    private String degreeName;      // Tên bằng cấp (Ví dụ: "Bachelor of Science")
    private String major;           // Ngành học (Ví dụ: "Computer Science")


    @OneToMany(
            mappedBy = "degree",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<DegreeDetail> degreeDetails;  // Danh sách chi tiết bằng cấp liên kết
}
