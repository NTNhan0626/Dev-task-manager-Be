package com.haku.devtask_manager.payload.entityrequest;

import com.haku.devtask_manager.entity.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDetailRequest {

    private String categoryDetailName;


}
