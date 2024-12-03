package com.haku.devtask_manager.payload.entityresponse;

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
public class CategoryDetailResponse {

    private Long categoryDetailId;

    private String categoryDetailName;


    private String categoryName;
    private Long categoryId;

}
