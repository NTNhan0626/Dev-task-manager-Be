package com.haku.devtask_manager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    USER_EXISTS("1000", "User already exists"),
    USER_NOT_FOUND("1001", "User not found"),
    INVALID_REQUEST("1002", "Invalid request"),
    INTERNAL_SERVER_ERROR("1003", "Internal server error"),

    ROLESDETAIL_EXISTS("1004", "roles detail already exists"),
    ROLESDETAIL_NOTEXISTS("1005", "roles detail already not exists"),

    DEPARTMENT_EXISTS("1006","department already exists"),
    DEPARTMENT_NOTEXISTS("1007","department already not exists"),

    CATEGORY_NOTEXISTS("108","category alreadt not exists"),
    CATEGORY_EXISTS("109","category alreadt exists"),

    INFORMATION_NOTEXISTS("110","information alreadt not exists"),
    INFORMATION_EXISTS("111","information alreadt exists");


    private final String code;
    private final String message;
}
