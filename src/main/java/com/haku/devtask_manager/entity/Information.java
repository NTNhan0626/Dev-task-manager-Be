package com.haku.devtask_manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long informationId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private boolean Gender;
    private Date dateOfBirth;

    @OneToOne()
    @JoinColumn(name = "accountId")
    private  Account account;

}
