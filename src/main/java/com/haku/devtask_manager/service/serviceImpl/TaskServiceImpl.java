package com.haku.devtask_manager.service.serviceImpl;

import com.haku.devtask_manager.entity.Account;
import com.haku.devtask_manager.entity.Project;
import com.haku.devtask_manager.entity.Task;
import com.haku.devtask_manager.entity.TaskDetail;
import com.haku.devtask_manager.exception.CustomRuntimeExceptionv2;
import com.haku.devtask_manager.exception.ExceptionCodev2;
import com.haku.devtask_manager.mapper.TaskMapper;
import com.haku.devtask_manager.payload.entityrequest.TaskRequest;
import com.haku.devtask_manager.payload.entityresponse.TaskDetailResponse;
import com.haku.devtask_manager.payload.entityresponse.TaskResponse;
import com.haku.devtask_manager.repository.AccountRepo;
import com.haku.devtask_manager.repository.ProjectRepo;
import com.haku.devtask_manager.repository.TaskDetailRepo;
import com.haku.devtask_manager.repository.TaskRepo;
import com.haku.devtask_manager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;
    private final ProjectRepo projectRepo;
    private final TaskDetailRepo taskDetailRepo;
    private final AccountRepo accountRepo;

    private final TaskMapper taskMapper;

    public TaskResponse convertToDto(Task task) {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setTaskId(task.getTaskId());
        taskResponse.setTaskName(task.getTaskName());
        taskResponse.setDescription(task.getDescription());
        taskResponse.setStatus(task.getStatus());
        taskResponse.setTaskCondition(task.getTaskCondition());
        taskResponse.setProgress(task.getProgress());
        taskResponse.setCreateDate(task.getCreateDate());
        taskResponse.setStartDate(task.getStartDate());
        taskResponse.setEndDate(task.getEndDate());
        taskResponse.setActualEndDate(task.getActualEndDate());
        taskResponse.setManagerTaskId(task.getManagerTaskId());
        taskResponse.setProjectId(task.getProject().getProjectId());
        taskResponse.setProjectName(task.getProject().getProjectName());
        taskResponse.setProjectCreaterId(task.getProject().getCreaterId());
        if(task.getParentTask()!= null) { // kiẻmr tra xem task cha có tồn tại hay không
            taskResponse.setParentManagerTaskId(task.getParentTask().getManagerTaskId());

            taskResponse.setParentTaskStatus(task.getParentTask().getStatus());
        }
        if(task.getManagerTaskId() != null){
            Account account = accountRepo.findById(task.getManagerTaskId()).orElseThrow();
            taskResponse.setParentTaskName(account.getUsername());
        }
        taskResponse.setTaskDetailResponses(task.getTaskDetails().stream()
                .map(taskDetail -> TaskDetailResponse.builder()
                        .status(taskDetail.getStatus())
                        .accountName(taskDetail.getAccount().getUsername())
                        .accountId(taskDetail.getAccount().getAccountId())
                        .taskName(taskDetail.getTask().getTaskName())
                        .joinDate(taskDetail.getJoinDate())
                        .build())
                .collect(Collectors.toList()));

        // Chuyển đổi danh sách task con nếu có
        if (task.getTaskList() != null) {
            List<TaskResponse> subTasks = task.getTaskList().stream()
                    .map(this::convertToDto) // Đệ quy để tạo cây phân cấp
                    .collect(Collectors.toList());
            taskResponse.setTaskResponseList(subTasks);
        }

        return taskResponse;
    }

    @Override
    public TaskResponse getTaskById(Long taskId) { // khi lấy 1 task bất kì sẽ lấy nó cùng với các task con của nó
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.TASK_NOT_FOUND));
        return convertToDto(task);
    }

    @Override
    public TaskResponse createTask(TaskRequest taskRequest, Long projectId, Long taskParentId) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.PROJECT_NOT_FOUND));
        // đặt biến kiểm tra trừ trường hợp tạo task cha thì sẽ k có taskparent

        Task task = taskMapper.toTask(taskRequest);
        if (taskParentId != null) {
            Task taskparent = taskRepo.findById(taskParentId)
                    .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.TASK_NOT_FOUND));
            task.setParentTask(taskparent);
        }

        task.setProject(project);
        taskRepo.save(task);
        // nếu tạo task mà có idmanager tức là đang có 1 người trong task tạo task con thì phải thêm ng đó vào task con
        //đây là trường hợp người dùng tạo node lá
        if(task.getManagerTaskId() != null){
            TaskDetail taskDetail = new TaskDetail();
            taskDetail.setTask(task);
            taskDetail.setAccount(accountRepo.findById(task.getManagerTaskId()).orElseThrow());
            taskDetail.setStatus("inTask");
            taskDetail.setJoinDate(new Date());
            taskDetailRepo.save(taskDetail);
        }
        return taskMapper.toTaskResponse(task);
    }

    @Override
    public TaskResponse updateTask(TaskRequest taskRequest, Long taskId) {
        Task task = taskRepo.findById(taskId).orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.TASK_NOT_FOUND));
        if(taskRequest.getTaskName()!=null) task.setTaskName(taskRequest.getTaskName());
        if(taskRequest.getDescription()!=null) task.setDescription(taskRequest.getDescription());
        if(taskRequest.getEndDate()!=null) task.setEndDate(taskRequest.getEndDate());
        if(taskRequest.getManagerTaskId()!=null) task.setManagerTaskId(taskRequest.getManagerTaskId());
        if(taskRequest.getCreateDate()!=null) task.setCreateDate(taskRequest.getCreateDate());
        if(taskRequest.getStartDate()!=null) task.setStartDate(taskRequest.getStartDate());
        if(taskRequest.getStatus()!=null) task.setStatus(taskRequest.getStatus());
        if(taskRequest.getActualEndDate()!=null) task.setActualEndDate(taskRequest.getActualEndDate());

        taskRepo.save(task);
        return convertToDto(task);
    }

    @Override
    public TaskResponse updateTaskProgress(Long taskId, String progress) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.TASK_NOT_FOUND));
        task.setProgress(progress);
        taskRepo.save(task);
        return convertToDto(task);
    }
}
