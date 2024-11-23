package com.haku.devtask_manager.payload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private int code;
    private String message;
    private T result;

    
}

