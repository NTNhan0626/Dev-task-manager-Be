package com.haku.devtask_manager.service.serviceImpl;

import com.haku.devtask_manager.entity.ProjectDetail;
import com.haku.devtask_manager.entity.Task;
import com.haku.devtask_manager.entity.TaskDetail;
import com.haku.devtask_manager.exception.CustomRuntimeExceptionv2;
import com.haku.devtask_manager.exception.ExceptionCodev2;
import com.haku.devtask_manager.payload.entityrequest.TaskDetailRequest;
import com.haku.devtask_manager.payload.entityrequest.TaskRequest;
import com.haku.devtask_manager.payload.entityresponse.TaskDetailResponse;
import com.haku.devtask_manager.payload.entityresponse.TaskResponse;
import com.haku.devtask_manager.repository.AccountRepo;
import com.haku.devtask_manager.repository.TaskDetailRepo;
import com.haku.devtask_manager.repository.TaskRepo;
import com.haku.devtask_manager.service.TaskDetailService;
import com.haku.devtask_manager.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
    public class TaskDetailServiceImpl implements TaskDetailService {
    private final TaskDetailRepo taskDetailRepo;
    private final TaskRepo taskRepo;
    private final AccountRepo accountRepo;


    @Override
    public TaskDetailResponse createTaskDetail(TaskDetailRequest taskDetailRequest, Long taskId, Long accountId) {
        return null;
    }

    @Override
    public List<TaskDetailResponse> createTaskDetailResponseList(Long taskId, List<Long> accountIds,Long managerTaskId) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.TASK_NOT_FOUND));
        accountIds.forEach(id ->{
            TaskDetail taskDetail = new TaskDetail();
            taskDetail.setAccount(accountRepo.findById(id).orElseThrow());
            taskDetail.setTask(taskRepo.findById(taskId).orElseThrow());
            taskDetail.setStatus("inTask");
            taskDetail.setJoinDate(new Date());
            taskDetailRepo.save(taskDetail);

            taskDetailRepo.save(taskDetail);
        } );

        task.setManagerTaskId(managerTaskId);
        taskRepo.save(task);
        List<TaskDetail> taskDetails = taskDetailRepo.findAllByTask_TaskId(taskId);
        return taskDetails.stream()
                .map(taskDetail -> TaskDetailResponse.builder()
                        .status(taskDetail.getStatus())
                        .joinDate(taskDetail.getJoinDate())
                        .taskName(taskDetail.getTask().getTaskName())
                        .accountName(taskDetail.getAccount().getUsername())
                        .taskDetailId(taskDetail.getTaskDetailId())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDetailResponse> getTaskDetailResponseList(Long taskId) {
        List<TaskDetail> taskDetails = taskDetailRepo.findAllByTask_TaskId(taskId);
        if(taskDetails.isEmpty()) throw new CustomRuntimeExceptionv2(ExceptionCodev2.TASK_NOT_FOUND);

        return taskDetails.stream()
                .map(taskDetail -> TaskDetailResponse.builder()
                        .taskDetailId(taskDetail.getTaskDetailId())
                        .taskName(taskDetail.getTask().getTaskName())
                        .accountName(taskDetail.getAccount().getUsername())
                        .status(taskDetail.getStatus())
                        .build())
                .collect(Collectors.toList());
    }
    @Transactional
    @Override
    public void deleteTaskDetail(Long taskdetailId) {
        TaskDetail taskDetail = taskDetailRepo.findById(taskdetailId).orElseThrow();
        log.info("task detail {}",taskdetailId);
        taskDetailRepo.delete(taskDetail);
    }
}
