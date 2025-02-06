package com.haku.devtask_manager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    USER_EXISTS("1000", "User already exists"),
    USER_NOT_FOUND("1001", "User not found"),
    USER_HAS_BEEN_BLOCKED("1014", "User has been blocked"),
    INVALID_REQUEST("1002", "Invalid request"),
    INTERNAL_SERVER_ERROR("1003", "Internal server error"),

    ROLESDETAIL_EXISTS("1004", "roles detail already exists"),
    ROLESDETAIL_NOTEXISTS("1005", "roles detail already not exists"),

    DEPARTMENT_EXISTS("1006","department already exists"),
    DEPARTMENT_NOTEXISTS("1007","department already not exists"),

    DEPARTMENT_DETAIL_EXISTS("1008","department detail already exists"),
    DEPARTMENT_DETAIL_NOTEXISTS("1009","department detail already not exists"),

    CATEGORY_NOTEXISTS("1010","category alreadt not exists"),
    CATEGORY_EXISTS("1011","category alreadt exists"),

    INFORMATION_NOTEXISTS("1012","information alreadt not exists"),
    INFORMATION_EXISTS("1013","information alreadt exists");


    private final String code;
    private final String message;
}
