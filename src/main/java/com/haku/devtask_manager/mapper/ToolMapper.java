package com.haku.devtask_manager.mapper;

import com.haku.devtask_manager.entity.Tool;
import com.haku.devtask_manager.payload.entityrequest.ToolRequest;
import com.haku.devtask_manager.payload.entityresponse.ToolResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ToolMapper {
    Tool toTool(ToolRequest toolRequest);
    ToolResponse toToolResponse (Tool tool);
    List<ToolResponse> toolResponseList (List<Tool> tools);
}
