package com.haku.devtask_manager.service.serviceImpl;

import com.haku.devtask_manager.entity.Tool;
import com.haku.devtask_manager.exception.CustomRuntimeException;
import com.haku.devtask_manager.exception.CustomRuntimeExceptionv2;
import com.haku.devtask_manager.exception.ExceptionCodev2;
import com.haku.devtask_manager.mapper.ToolMapper;
import com.haku.devtask_manager.mapper.ToolProjectDetailMapper;
import com.haku.devtask_manager.payload.entityrequest.ToolRequest;
import com.haku.devtask_manager.payload.entityresponse.ToolResponse;
import com.haku.devtask_manager.repository.ToolRepo;
import com.haku.devtask_manager.service.ToolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToolServiceImpl implements ToolService {
    private final ToolRepo toolRepo;
    private final ToolMapper toolMapper;
    private final ToolProjectDetailMapper toolProjectDetailMapper;
    @Override
    public ToolResponse createTool(ToolRequest toolRequest) {
        Tool tool = toolMapper.toTool(toolRequest);
        toolRepo.save(tool);
        return null;
    }

    @Override
    public List<ToolResponse> findAllTool() {
        List<Tool> tools = toolRepo.findAll();
        if (tools.isEmpty()){
            throw new CustomRuntimeExceptionv2(ExceptionCodev2.TOOL_NOT_FOUND);
        }
        return tools.stream()
                .map(tool -> {
                    ToolResponse toolResponse = toolMapper.toToolResponse(tool);
                    toolResponse.setToolProjectDetailResponses(toolProjectDetailMapper.toToolProjectDetailResponses(tool.getProjectToolDetails()));
                    return toolResponse;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ToolResponse updateTool(ToolRequest toolRequest, Long toolId) {
        Tool tool = toolRepo.findById(toolId)
                .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.TOOL_NOT_FOUND));
        if(toolRequest.getToolType()!=null) tool.setToolType(toolRequest.getToolType());
        if(toolRequest.getDescription()!=null) tool.setDescription(toolRequest.getDescription());
        if(toolRequest.getQuantityAvailable() != -1) tool.setQuantityAvailable(toolRequest.getQuantityAvailable());
        if(toolRequest.getUpdatedDate()!=null) tool.setUpdatedDate(toolRequest.getUpdatedDate());
        toolRepo.save(tool);

        return toolMapper.toToolResponse(tool);
    }
}
