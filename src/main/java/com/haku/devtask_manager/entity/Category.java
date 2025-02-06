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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String categoryName;
//    private String job;
//    private String title;
//    private String degree;
//    private String certificate;
//    private String skill;
//    private String tools;
//    private String province;
//    private String city;
//    private String projectType;


    @ManyToOne()
    @JoinColumn(name = "accountId")
    private Account account;

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<CategoryDetail> categoryDetails;


}
