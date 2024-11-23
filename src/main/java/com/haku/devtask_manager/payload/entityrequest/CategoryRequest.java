package com.haku.devtask_manager.payload.entityrequest;

import com.haku.devtask_manager.entity.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CategoryRequest {

    private String job;
    private String title;
    private String degree;
    private String certificate;
    private String skill;
    private String tools;
    private String province;
    private String city;
    private String projectType;


}
