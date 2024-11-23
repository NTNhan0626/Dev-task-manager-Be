package com.haku.devtask_manager.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomRuntimeException extends RuntimeException{
    private String code;
    private String message;

    public CustomRuntimeException(String message) {
        super(message);
     //   this.code = code;
    }
}
