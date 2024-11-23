package com.haku.devtask_manager.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class RolesDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean manager;
    private boolean staff;
    private boolean guest;
    private boolean reader;
    private boolean writer;
    private boolean creater;
    private boolean deleter;


    @ManyToOne()
    @JoinColumn(name = "accountId")
    private Account account;

    @ManyToOne()
    @JoinColumn(name = "rolesId")
    private Roles roles;

}
