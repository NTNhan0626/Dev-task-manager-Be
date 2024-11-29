package com.haku.devtask_manager.mapper;

import com.haku.devtask_manager.entity.LogWork;
import com.haku.devtask_manager.payload.entityrequest.LogWorkRequest;
import com.haku.devtask_manager.payload.entityresponse.LogWorkResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LogworkMapper {
    LogWork toLogWork (LogWorkRequest logWorkRequest);
    LogWorkResponse toLogWorkResponse(LogWork logWork);
    List<LogWorkResponse> toLogWorkResponseList (List<LogWork> logWorks);
}
