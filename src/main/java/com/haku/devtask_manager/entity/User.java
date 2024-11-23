package com.haku.devtask_manager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;



}
