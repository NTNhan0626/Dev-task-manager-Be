package com.haku.devtask_manager.payload.entityresponse;

import com.haku.devtask_manager.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CategoryResPonse {
    private Long categoryId;
    private String categoryName;
    private String accountName;
    private Long accountId;
    List<CategoryDetailResponse> categoryDetailResponses;


}
