package com.haku.devtask_manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long specializationId;

    private String specializationName;

    @OneToMany(mappedBy = "specialization", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<SpecializationDetail> specializationDetails;

}
