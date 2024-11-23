package com.haku.devtask_manager.exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErorResponse {
    private String code;
    private String message;
}
