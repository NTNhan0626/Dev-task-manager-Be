package com.haku.devtask_manager.service.serviceImpl;

import com.haku.devtask_manager.entity.Account;
import com.haku.devtask_manager.entity.Task;
import com.haku.devtask_manager.entity.TaskEvaluation;
import com.haku.devtask_manager.exception.CustomRuntimeException;
import com.haku.devtask_manager.exception.CustomRuntimeExceptionv2;
import com.haku.devtask_manager.exception.ExceptionCode;
import com.haku.devtask_manager.exception.ExceptionCodev2;
import com.haku.devtask_manager.mapper.TaskEvaluationMapper;
import com.haku.devtask_manager.payload.entityrequest.TaskEvaluationRequest;
import com.haku.devtask_manager.payload.entityresponse.TaskEvaluationResponse;
import com.haku.devtask_manager.payload.entityresponse.TaskResponse;
import com.haku.devtask_manager.repository.AccountRepo;
import com.haku.devtask_manager.repository.TaskEvaluationRepo;
import com.haku.devtask_manager.repository.TaskRepo;
import com.haku.devtask_manager.service.TaskEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskEvaluationServiceImpl implements TaskEvaluationService {
    private final TaskEvaluationRepo taskEvaluationRepo;
    private final AccountRepo accountRepo;
    private final TaskRepo taskRepo;
    private final TaskEvaluationMapper taskEvaluationMapper;

    @Override
    public TaskEvaluationResponse createTaskEvaluation(TaskEvaluationRequest taskEvaluationRequest, Long accountId, Long taskId) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new CustomRuntimeException(ExceptionCode.USER_NOT_FOUND.getCode(),ExceptionCode.USER_NOT_FOUND.getMessage()));
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.TASK_NOT_FOUND));

        TaskEvaluation taskEvaluation = taskEvaluationMapper.toTaskEvaluation(taskEvaluationRequest);
        taskEvaluation.setAccount(account);
        taskEvaluation.setTask(task);
        taskEvaluationRepo.save(taskEvaluation);
        return taskEvaluationMapper.toTaskEvaluationResponse(taskEvaluation);
    }

    @Override
    public TaskEvaluationResponse getTaskEvaluationInTask(Long taskId) {
        TaskEvaluation taskEvaluation = taskEvaluationRepo.findOneByTask_TaskId(taskId);
        if (taskEvaluation == null){
            throw new CustomRuntimeExceptionv2(ExceptionCodev2.TASKEVALUATION_NOT_FOUND);
        }

        return TaskEvaluationResponse.builder()
                .taskName(taskEvaluation.getTask().getTaskName())
                .taskId(taskEvaluation.getTask().getTaskId())
                .accountId(taskEvaluation.getAccount().getAccountId())
                .accountName(taskEvaluation.getAccount().getUsername())
                .taskEvaluationId(taskEvaluation.getTaskEvaluationId())
                .build();
    }

    @Override
    public TaskEvaluationResponse updateTaskEvaluation(TaskEvaluationRequest taskEvaluationRequest, Long taskEvaluationId) {
        TaskEvaluation taskEvaluation = taskEvaluationRepo.findById(taskEvaluationId)
                .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.TASKEVALUATION_NOT_FOUND));
        taskEvaluation.setFeedback(taskEvaluationRequest.getFeedback());
        taskEvaluation.setQuantity(taskEvaluationRequest.getQuantity());
        taskEvaluationRepo.save(taskEvaluation);

        return taskEvaluationMapper.toTaskEvaluationResponse(taskEvaluation);
    }

    @Override
    public TaskEvaluationResponse deleteTaskEvaluation(Long taskEvaluationId) {
        TaskEvaluation taskEvaluation = taskEvaluationRepo.findById(taskEvaluationId)
                .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.TASKEVALUATION_NOT_FOUND));
        taskEvaluationRepo.delete(taskEvaluation);
        return taskEvaluationMapper.toTaskEvaluationResponse(taskEvaluation);
    }
}
