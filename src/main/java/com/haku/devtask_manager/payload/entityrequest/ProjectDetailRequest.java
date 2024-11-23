package com.haku.devtask_manager.payload.entityrequest;

import com.haku.devtask_manager.entity.Account;
import com.haku.devtask_manager.entity.Project;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDetailRequest {

    private String status;
}
