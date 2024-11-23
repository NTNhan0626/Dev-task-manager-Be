package com.haku.devtask_manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rolesId;
    private String rolesName;

    @OneToMany(mappedBy = "roles",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<RolesDetail> rolesDetailList;

}
