package com.haku.devtask_manager.service.serviceImpl;

import com.haku.devtask_manager.entity.*;
import com.haku.devtask_manager.exception.CustomRuntimeException;
import com.haku.devtask_manager.exception.CustomRuntimeExceptionv2;
import com.haku.devtask_manager.exception.ExceptionCode;
import com.haku.devtask_manager.exception.ExceptionCodev2;
import com.haku.devtask_manager.mapper.AccountMapper;
import com.haku.devtask_manager.mapper.ProjectMapper;
import com.haku.devtask_manager.payload.entityrequest.ProjectRequest;
import com.haku.devtask_manager.payload.entityresponse.*;
import com.haku.devtask_manager.repository.*;
import com.haku.devtask_manager.service.DepartmentDetailService;
import com.haku.devtask_manager.service.ProjectService;
import com.haku.devtask_manager.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepo projectRepo;
    private final ProjectDepartmentDetailRepo projectDepartmentDetailRepo;
    private final ProjectDetailRepo projectDetailRepo;
    private final DepartmentRepo departmentRepo;
    private final AccountRepo accountRepo;
    private final RolesRepo rolesRepo;
    private final RolesDetailRepo rolesDetailRepo;
    private final TaskRepo taskRepo;
    private final TaskService taskService;

    private final ProjectMapper projectMapper;
    private final AccountMapper accountMapper;

    private final DepartmentDetailService departmentDetailService;
    @Transactional
    @Override
    // áp dụng cho dự án của phòng ban
    public ProjectResponse createDepartmentProject(ProjectRequest projectRequest, Long departmentId) {
        //khi tạo 1 dự án mới từ 1 phòng nào đó thì đồng thời phải tạo chi tiết dự án phòng ban tương ứng
        Project project = projectMapper.toProject(projectRequest);
        projectRepo.save(project);

        ProjectDepartmentDetail projectDepartmentDetail = new ProjectDepartmentDetail();
        projectDepartmentDetail.setDepartment(departmentRepo.findById(departmentId)
                .orElseThrow(
                () -> new CustomRuntimeException(ExceptionCode.DEPARTMENT_NOTEXISTS.getCode(),ExceptionCode.DEPARTMENT_NOTEXISTS.getMessage())
        ));
        projectDepartmentDetail.setProject(project);
        projectDepartmentDetail.setStatus("inDepartment");
        projectDepartmentDetailRepo.save(projectDepartmentDetail);
        // đồng thời tạo công việc đầu tiên của dự án là công việc cha có tên trùng với tên tự án
        Task task = new Task();
        task.setTaskName(project.getProjectName());
        task.setCreateDate(project.getCreatedDate());
        task.setManagerTaskId(project.getProjectManagerId());
        task.setProject(project);
        task.setEndDate(project.getEndDate());
        task.setTaskCondition(project.getProjectCondition());
        task.setStatus("Pending");
        task.setTaskCondition("Active");
        taskRepo.save(task);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public List<ProjectResponse> getAllProject() {
        List<Project> projects =  projectRepo.findAll();
        if(projects.isEmpty()) throw new CustomRuntimeExceptionv2(ExceptionCodev2.PROJECT_NOT_FOUND);
        return projects.stream()
                .map(project -> {
                    ProjectResponse projectResponse = projectMapper.toProjectResponse(project);
                    projectResponse.setProjectId(project.getProjectId());
                    return projectResponse;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectResponse> getAllByDepartmentIdIdAndProjectType(Long departmentId, boolean projectType) {
        List<ProjectDepartmentDetail> projectDepartmentDetails = projectDepartmentDetailRepo.findAllByDepartment_DepartmentId(departmentId);
        if (projectDepartmentDetails.isEmpty()) throw new CustomRuntimeExceptionv2(ExceptionCodev2.PROJECTDEPARTMENTDETAIL_NOT_FOUND);
        List<Long> projectIds = projectDepartmentDetails.stream()
                .map(projectDepartmentDetail -> projectDepartmentDetail.getProject().getProjectId())
                .toList();
        List<Project> projects = projectRepo.findAllById(projectIds);
        if (projects.isEmpty()) throw new CustomRuntimeExceptionv2(ExceptionCodev2.PROJECT_NOT_FOUND);

        return projects.stream()
                .map(project -> {
                    ProjectResponse projectResponse = projectMapper.toProjectResponse(project);
                    projectResponse.setProjectDetailResponses(project.getProjectDetails().stream()
                            .map(projectDetail -> ProjectDetailResponse.builder()
                                    .projectDetailId(projectDetail.getProjectDetailId())
                                    .userName(projectDetail.getAccount().getUsername())
                                    .joinDate(projectDetail.getJoinDate())
                                    .accountId(projectDetail.getAccount().getAccountId())
                                    .status(projectDetail.getStatus())
                                    .build())
                            .collect(Collectors.toList()));
                    return projectResponse;
                })
                .toList();
    }

    @Override
    public ProjectResponse getOneByProjectId(Long projectId) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.PROJECT_NOT_FOUND));
        ProjectResponse projectResponse = projectMapper.toProjectResponse(project);

        boolean checked = projectDetailRepo.existsByProject_ProjectId(projectId);
        if(checked){
            List<ProjectDetail> projectDetails = projectDetailRepo.findAllByProject_ProjectId(projectId);
            projectResponse.setProjectDetailResponses(projectDetails.stream()
                    .map(projectDetail -> ProjectDetailResponse.builder()
                            .joinDate(projectDetail.getJoinDate())
                            .userName(projectDetail.getAccount().getUsername())
                            .status(projectDetail.getStatus())
                            .accountId(projectDetail.getAccount().getAccountId())
                            .taskResponses(projectDetail.getProject().getTaskList().stream()
                                    .filter(task -> {
                                        List<Long> accountIds = task.getTaskDetails().stream().map(taskDetail -> taskDetail.getAccount().getAccountId()).toList();
                                        return (accountIds.contains(projectDetail.getAccount().getAccountId()) && !task.getTaskList().isEmpty());
                                    })
                                    .map(task -> TaskResponse.builder()
                                            .taskName(task.getTaskName())
                                    .build()).collect(Collectors.toList()))
                            .build() )
                    .collect(Collectors.toList()));
        }

        return projectResponse;
    }

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
        taskResponse.setProjectManagerId(task.getProject().getProjectManagerId());
        if(task.getParentTask()!= null) { // kiẻmr tra xem task cha có tồn tại hay không
            taskResponse.setParentManagerTaskId(task.getParentTask().getManagerTaskId());

            taskResponse.setParentTaskStatus(task.getParentTask().getStatus());
            taskResponse.setParentTaskEmployeeSize(task.getParentTask().getTaskDetails().size());
            taskResponse.setParentTaskId(task.getParentTask().getTaskId());
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
    public List<TaskResponse> getTaskInProject(Long projectId) {
        Task task = taskRepo.getOneByProject_ProjectIdAndParentTask_TaskIdIsNull(projectId);
        if (task == null) throw new CustomRuntimeExceptionv2(ExceptionCodev2.TASK_NOT_FOUND);
        List<TaskResponse> taskResponses = new ArrayList<>();
        taskResponses.add(convertToDto(task));
        return taskResponses;
    }

    @Override
    public List<AccountResponse> getAccountInproject(Long projectId) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.PROJECT_NOT_FOUND));
        List<Long> accountIds = project.getProjectDetails().stream()
                .map(projectDetail -> projectDetail.getAccount().getAccountId())
                .toList();
        if (accountIds.isEmpty()) throw new CustomRuntimeException(ExceptionCode.USER_NOT_FOUND.getCode(),ExceptionCode.USER_NOT_FOUND.getMessage());
        List<Account> accounts = accountRepo.findAllById(accountIds);

        return accounts.stream().
                map(account -> {

                    ProjectDetail projectD = projectDetailRepo.findOneByAccount_AccountIdAndProject_ProjectId(account.getAccountId(),projectId);

                    return AccountResponse.builder()
                            .username(account.getUsername())
                            .accountId(account.getAccountId())
                            .statusInProject(projectD.getStatus())
                            .specializations(account.getSpecializationDetails().stream().map(
                                    specializationDetail -> specializationDetail.getSpecialization().getSpecializationName()
                            ).collect(Collectors.joining(",")))
                            .statusProject(
                                    Optional.ofNullable(account.getProjectDetails()).filter(projectDetails -> projectDetails.stream()
                                            .anyMatch(projectDetail -> "inproject".equals(projectDetail.getStatus()))).map(projectDetails -> "Đang trong dự án: " + projectDetails.stream()
                                            .filter(projectDetail -> "inproject".equals(projectDetail.getStatus()))
                                            .map(projectDetail -> projectDetail.getProject().getProjectName())
                                            .collect(Collectors.joining(","))).orElse("Rảnh")  // Nếu getProjectDetails() là null thì trả về "Rảnh"
                            )
                            .build();
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectResponse> getAllByAccountId(Long accountId) {
        // lấy tất cả các id dự án mà nhan viên tham gia
        List<Long> projectIds = projectDetailRepo.findAllByAccount_accountId(accountId).stream()
                .map(projectDetail -> projectDetail.getProject().getProjectId())
                .toList();
        if (projectIds.isEmpty()) throw new CustomRuntimeExceptionv2(ExceptionCodev2.PROJECT_NOT_FOUND);

        List<Project> projects = projectRepo.findAllById(projectIds);

        return projectMapper.toProjectResponseList(projects);
    }

    @Override
    public ProjectResponse updateProject(ProjectRequest projectRequest, Long projectId) {
        // Tìm kiếm Project theo ID, nếu không thấy thì ném ngoại lệ
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.PROJECT_NOT_FOUND));

        // Cập nhật các trường nếu không null
        if (projectRequest.getProjectName() != null) project.setProjectName(projectRequest.getProjectName());
        if (projectRequest.getDescription() != null) project.setDescription(projectRequest.getDescription());
        if (projectRequest.getCreatedDate() != null) project.setCreatedDate(projectRequest.getCreatedDate());
        if (projectRequest.getStartDate() != null) project.setStartDate(projectRequest.getStartDate());
        if (projectRequest.getEndDate() != null) project.setEndDate(projectRequest.getEndDate());
        if (projectRequest.getActualEndDate() != null) project.setActualEndDate(projectRequest.getActualEndDate());
        if (projectRequest.getStatus() != null) project.setStatus(projectRequest.getStatus());
        if (projectRequest.getProjectCondition() != null) project.setProjectCondition(projectRequest.getProjectCondition());

        // Lưu lại các thay đổi vào cơ sở dữ liệu
        projectRepo.save(project);
        // khi tahm dừng dự án thì phải tạm dừng tất cár công việc của dự án đó
        if (projectRequest.getProjectCondition()!=null && projectRequest.getProjectCondition().equals("Paused") && !project.getTaskList().isEmpty()){
            List<Task> taskList = taskRepo.findAllByProject_ProjectId(projectId);
            taskList.forEach(task -> {
                task.setTaskCondition(projectRequest.getProjectCondition());
                taskRepo.save(task);
            });

        }

        // khi tiếp tục dự án thì phải tiếp tục tất cả công việc của dự án đó
        if (projectRequest.getProjectCondition()!=null && projectRequest.getProjectCondition().equals("Active") && !project.getTaskList().isEmpty()){
            List<Task> taskList = taskRepo.findAllByProject_ProjectId(projectId);
            taskList.forEach(task -> {
                task.setTaskCondition(projectRequest.getProjectCondition());
                taskRepo.save(task);
            });

        }
        ProjectResponse projectResponse = projectMapper.toProjectResponse(project);
        // khi hủy dự án thì phải xóa tất cả công việc của dự án đó
        if (projectRequest.getProjectCondition()!=null && projectRequest.getProjectCondition().equals("Canceled") && !project.getTaskList().isEmpty()){
            Task task = taskRepo.findOneByProject_ProjectIdAndParentTaskIsNull(projectId);
            projectResponse.setTaskParentId(task.getTaskId());
        }

        // Chuyển đổi Project thành ProjectResponse và trả về
        return projectResponse;
    }

    @Transactional
    @Override
    public ProjectResponse createInterDepartmetalProject(ProjectRequest projectRequest) {
        Project project = projectMapper.toProject(projectRequest);
        projectRepo.save(project);

        // thêm các phòng ban vào dự án
        projectRequest.getDepartmentIds().forEach(departmentId ->{
            Department department = departmentRepo.findById(departmentId)
                    .orElseThrow(() -> new CustomRuntimeException(ExceptionCode.DEPARTMENT_NOTEXISTS.getCode(),ExceptionCode.DEPARTMENT_NOTEXISTS.getMessage()));
            ProjectDepartmentDetail projectDepartmentDetail = new ProjectDepartmentDetail();
            projectDepartmentDetail.setDepartment(department);
            projectDepartmentDetail.setProject(project);
            projectDepartmentDetail.setStatus("inDepartment");
            projectDepartmentDetailRepo.save(projectDepartmentDetail);
        } );

        // tạo công việc đầu tiền có tên trùng với dự án
        Task task = new Task();
        task.setTaskName(project.getProjectName());
        task.setCreateDate(project.getCreatedDate());
        task.setManagerTaskId(project.getProjectManagerId());
        task.setProject(project);
        task.setEndDate(project.getEndDate());
        task.setTaskCondition(project.getProjectCondition());
        taskRepo.save(task);
        //đồng thời thêm người được chọn làm quản lí dự án vào dự án
        Account account = accountRepo.findById(projectRequest.getProjectManagerId()).orElseThrow();
        ProjectDetail projectDetail = new ProjectDetail();
        projectDetail.setAccount(account);
        projectDetail.setProject(project);
        projectDetail.setJoinDate(new Date());
        projectDetail.setStatus("inproject");
        projectDetailRepo.save(projectDetail);

        // và tạo 1 quyền cho dự án để cấp cho người quản lí có thể là bất cứ ai

        if(rolesRepo.existsByRolesName("PROJECT_MANAGER")){
            Roles roles = rolesRepo.findOneByRolesName("PROJECT_MANAGER");
            RolesDetail rolesDetail = new RolesDetail();
            rolesDetail.setAccount(account);
            rolesDetail.setRoles(roles);
            rolesDetailRepo.save(rolesDetail);
        }else{
            Roles roles = new Roles();
            roles.setRolesName("PROJECT_MANAGER");
            rolesRepo.save(roles);

            RolesDetail rolesDetail = new RolesDetail();
            rolesDetail.setAccount(account);
            rolesDetail.setRoles(roles);
            rolesDetailRepo.save(rolesDetail);
        }


        return projectMapper.toProjectResponse(project);
    }

    @Override
    public List<ProjectResponse> getAllInterDepartmetalProject(boolean type) {

        List<Project> projects = projectRepo.findAllByProjectType(type);
        if (projects.isEmpty()) throw new CustomRuntimeExceptionv2(ExceptionCodev2.PROJECT_NOT_FOUND);

        return projects.stream()
                .map(project -> {
                    ProjectResponse projectResponse = projectMapper.toProjectResponse(project);
                    projectResponse.setProjectDepartmentDetailResponses(project.getProjectDepartmentDetails().stream()
                            .map(projectDepartmentDetail -> ProjectDepartmentDetailResponse.builder()
                                    .departmentName(projectDepartmentDetail.getDepartment().getDepartmentName())
                                    .departmentId(projectDepartmentDetail.getDepartment().getDepartmentId())
                                    .status(projectDepartmentDetail.getStatus())

                                    .build())
                            .collect(Collectors.toList()));
                    return projectResponse;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<AccountResponse> getAllAccountsNotInProject(Long projectId, Long departmentId) {
        List<AccountResponse> accountResponseList = departmentDetailService.getDepartmentDetailsByDepartmentIdandNullTimeOut(departmentId);
        if (accountResponseList.isEmpty()) throw new CustomRuntimeException(ExceptionCode.USER_NOT_FOUND.getCode(),ExceptionCode.USER_NOT_FOUND.getMessage());

        Set<Long> accountIdsinProject = projectDetailRepo.findAllByProject_ProjectId(projectId).stream()
                .map(projectDetail -> projectDetail.getAccount().getAccountId())
                .collect(Collectors.toSet());

        return accountResponseList.stream()
                .filter(accountResponse -> !accountIdsinProject.contains(accountResponse.getAccountId()))
                .collect(Collectors.toList());

    }

}
