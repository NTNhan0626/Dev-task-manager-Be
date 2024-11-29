package com.haku.devtask_manager.service.serviceImpl;

import com.haku.devtask_manager.entity.LogWork;
import com.haku.devtask_manager.entity.Task;
import com.haku.devtask_manager.exception.CustomRuntimeExceptionv2;
import com.haku.devtask_manager.exception.ExceptionCodev2;
import com.haku.devtask_manager.mapper.LogworkMapper;
import com.haku.devtask_manager.payload.entityrequest.LogWorkRequest;
import com.haku.devtask_manager.payload.entityresponse.LogWorkResponse;
import com.haku.devtask_manager.repository.LogWorkRepo;
import com.haku.devtask_manager.repository.TaskRepo;
import com.haku.devtask_manager.service.LogworkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LogworkServiceImpl implements LogworkService {

    private final LogWorkRepo logWorkRepo;
    private final TaskRepo taskRepo;
    private final LogworkMapper logworkMapper;
    @Override
    public List<LogWorkResponse> getLogworkByTaskId(Long taskId) {
        List<LogWork> logWorks = logWorkRepo.findAllByTask_TaskId(taskId);
        if (logWorks.isEmpty()) throw new CustomRuntimeExceptionv2(ExceptionCodev2.LOGWORK_NOT_FOUND);
        return logWorks.stream()
                .map(logWork -> LogWorkResponse.builder()
                        .logworkId(logWork.getLogworkId())
                        .logworkName(logWork.getLogworkName())
                        .description(logWork.getDescription())
                        .time(logWork.getTime())
                        .status(logWork.getStatus())
                        .taskId(logWork.getTask().getTaskId())
                        .taskName(logWork.getTask().getTaskName())
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    public LogWorkResponse createLogwork(LogWorkRequest logWorkRequest, Long taskId) {
        LogWork logWork =logworkMapper.toLogWork(logWorkRequest);
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.TASK_NOT_FOUND));
        logWork.setTask(task);
        logWorkRepo.save(logWork);
        LogWorkResponse logWorkResponse = logworkMapper.toLogWorkResponse(logWork);
        logWorkResponse.setTaskName(task.getTaskName());
        logWorkResponse.setTaskId(taskId);
        return logWorkResponse;
    }

    @Override
    public LogWorkResponse updateLogworkStatus(Long logworkId, String status) {
        LogWork logWork = logWorkRepo.findById(logworkId)
                .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.LOGWORK_NOT_FOUND));
        logWork.setStatus(status);
        logWorkRepo.save(logWork);
        return logworkMapper.toLogWorkResponse(logWork);
    }


}
