package com.haku.devtask_manager.exception;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionCodev2 {
    DEGREE_EXISTS(112, "degree already exists", HttpStatus.CONFLICT),
    DEGREE_NOT_FOUND(113, "degree not found",HttpStatus.NOT_FOUND),

    DEGREEDETAIL_EXISTS(114, "degreedetail already exists", HttpStatus.CONFLICT),
    DEGREEDETAIL_NOT_FOUND(115, "degreedetail not found",HttpStatus.NOT_FOUND),

    SPECIALIZATION_EXISTS(116, "specialization already exists", HttpStatus.CONFLICT),
    SPECIALIZATION_NOT_FOUND(117, "specialization not found",HttpStatus.NOT_FOUND),

    PROJECT_EXISTS(118, "project already exists", HttpStatus.CONFLICT),
    PROJECT_NOT_FOUND(119, "project not found",HttpStatus.NOT_FOUND),

    PROJECTDETAIL_EXISTS(120, "project detail already exists", HttpStatus.CONFLICT),
    PROJECTDETAIL_NOT_FOUND(121, "project detail not found",HttpStatus.NOT_FOUND),

    PROJECTDEPARTMENTDETAIL_EXISTS(122, "project department detail already exists", HttpStatus.CONFLICT),
    PROJECTDEPARTMENTDETAIL_NOT_FOUND(123, "project department detail not found",HttpStatus.NOT_FOUND),

    TASK_EXISTS(124, "task already exists", HttpStatus.CONFLICT),
    TASK_NOT_FOUND(125, "task not found",HttpStatus.NOT_FOUND),

    LOGWORK_EXISTS(126, "logwork already exists", HttpStatus.CONFLICT),
    LOGWORK_NOT_FOUND(127, "logwork not found",HttpStatus.NOT_FOUND),

    TOOL_EXISTS(128, "tool already exists", HttpStatus.CONFLICT),
    TOOL_NOT_FOUND(129, "tool not found",HttpStatus.NOT_FOUND),

    ISSUES_EXISTS(130, "issues already exists", HttpStatus.CONFLICT),
    ISSUES_NOT_FOUND(131, "issues not found",HttpStatus.NOT_FOUND),

    ISSUES_DETAIL_EXISTS(132, "issues detail already exists", HttpStatus.CONFLICT),
    ISSUES_DETAIL_NOT_FOUND(133, "issues detail not found",HttpStatus.NOT_FOUND),

    TASKEVALUATION_EXISTS(134, "task evaluation already exists", HttpStatus.CONFLICT),
    TASKEVALUATION_NOT_FOUND(135, "task evaluation not found",HttpStatus.NOT_FOUND),

    CATEGORYDETAIL_EXISTS(136, "category detail already exists", HttpStatus.CONFLICT),
    CATEGORYDETAIL_NOT_FOUND(137, "category detail not found",HttpStatus.NOT_FOUND),


    ;

    private int code;
    private String message;
    private HttpStatus httpStatus;


}
