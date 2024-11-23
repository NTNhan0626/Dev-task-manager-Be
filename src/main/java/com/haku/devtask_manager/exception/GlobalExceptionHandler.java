package com.haku.devtask_manager.exception;

import com.haku.devtask_manager.payload.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<String> handlingRuntimeException(RuntimeException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
    @ExceptionHandler(value = CustomRuntimeException.class)
    public ResponseEntity<ErorResponse> handlingCustomRuntimeException(CustomRuntimeException customRuntimeException){
        return ResponseEntity.badRequest().body(ErorResponse.builder()
                        .code(customRuntimeException.getCode())
                        .message(customRuntimeException.getMessage())
                .build());
    }
    @ExceptionHandler(value = CustomRuntimeExceptionv2.class)
    public ResponseEntity<ApiResponse> handlingCustomRuntimeException2(CustomRuntimeExceptionv2 customRuntimeExceptionv2){
        ExceptionCodev2 exceptionCodev2 = customRuntimeExceptionv2.getExceptionCodev2();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(exceptionCodev2.getCode());
        apiResponse.setMessage(exceptionCodev2.getMessage());
        return ResponseEntity.status(exceptionCodev2.getHttpStatus()).body(apiResponse);
    }
}
