package com.haku.devtask_manager.mapper;

import com.haku.devtask_manager.entity.Tool;
import com.haku.devtask_manager.entity.ToolProjectDetail;
import com.haku.devtask_manager.payload.entityrequest.ToolProjectDetailRequest;
import com.haku.devtask_manager.payload.entityrequest.ToolRequest;
import com.haku.devtask_manager.payload.entityresponse.ToolProjectDetailResponse;
import com.haku.devtask_manager.payload.entityresponse.ToolResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ToolProjectDetailMapper {
    ToolProjectDetail toToolProjectDetail(ToolProjectDetailRequest toolProjectDetailRequest);
    ToolProjectDetailResponse toToolProjectDetailResponse (ToolProjectDetail  toolProjectDetail);
    List<ToolProjectDetailResponse> toToolProjectDetailResponses (List<ToolProjectDetail> toolProjectDetails);
}
