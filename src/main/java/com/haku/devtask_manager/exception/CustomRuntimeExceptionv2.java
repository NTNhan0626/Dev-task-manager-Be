package com.haku.devtask_manager.exception;

public class CustomRuntimeExceptionv2 extends RuntimeException{


    public CustomRuntimeExceptionv2(ExceptionCodev2 exceptionCodev2) {
        super(exceptionCodev2.getMessage());
        this.exceptionCodev2 = exceptionCodev2;
    }

    private ExceptionCodev2 exceptionCodev2;

    public ExceptionCodev2 getExceptionCodev2() {
        return exceptionCodev2;
    }

    public void setExceptionCodev2(ExceptionCodev2 exceptionCodev2) {
        this.exceptionCodev2 = exceptionCodev2;
    }
}
