package com.haku.devtask_manager.service.serviceImpl;

import com.haku.devtask_manager.entity.Account;
import com.haku.devtask_manager.entity.ProjectDetail;
import com.haku.devtask_manager.exception.CustomRuntimeExceptionv2;
import com.haku.devtask_manager.exception.ExceptionCodev2;
import com.haku.devtask_manager.payload.entityrequest.ProjectDetailRequest;
import com.haku.devtask_manager.payload.entityresponse.ProjectDetailResponse;
import com.haku.devtask_manager.repository.AccountRepo;
import com.haku.devtask_manager.repository.ProjectDetailRepo;
import com.haku.devtask_manager.repository.ProjectRepo;
import com.haku.devtask_manager.service.ProjectDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectDetailServiceImpl implements ProjectDetailService {
    private final ProjectDetailRepo projectDetailRepo;
    private final AccountRepo accountRepo;
    private final ProjectRepo projectRepo;

    @Transactional
    @Override
    public List<ProjectDetailResponse> createByProjectIdAndAccountIds(Long projectId, List<Long> accountIds) {
        accountIds.forEach(id ->{
            ProjectDetail projectDetail = new ProjectDetail();
            projectDetail.setAccount(accountRepo.findById(id).orElseThrow());
            projectDetail.setProject(projectRepo.findById(projectId).orElseThrow());
            projectDetail.setStatus("inproject");
            projectDetail.setJoinDate(new Date());
            projectDetailRepo.save(projectDetail);
        } );
        List<ProjectDetail> projectDetails = projectDetailRepo.findAllByProject_ProjectId(projectId);

        return projectDetails.stream()
                .map(projectDetail -> ProjectDetailResponse.builder()
                        .status(projectDetail.getStatus())
                        .userName(projectDetail.getAccount().getUsername())
                        .joinDate(projectDetail.getJoinDate())
                        .accountId(projectDetail.getAccount().getAccountId())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDetailResponse> createByProjectIdAndAccountIdsAndStatus(Long projectId, List<Long> accountIds, String status) {
        accountIds.forEach(id ->{
            ProjectDetail projectDetail = new ProjectDetail();
            projectDetail.setAccount(accountRepo.findById(id).orElseThrow());
            projectDetail.setProject(projectRepo.findById(projectId).orElseThrow());
            projectDetail.setStatus(status);
            projectDetail.setJoinDate(new Date());
            projectDetailRepo.save(projectDetail);
        } );
        List<ProjectDetail> projectDetails = projectDetailRepo.findAllByProject_ProjectId(projectId);

        return projectDetails.stream()
                .map(projectDetail -> ProjectDetailResponse.builder()
                        .status(projectDetail.getStatus())
                        .userName(projectDetail.getAccount().getUsername())
                        .joinDate(projectDetail.getJoinDate())
                        .accountId(projectDetail.getAccount().getAccountId())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDetailResponse> getAllAccountRequest(Long projectId, String status) {
        List<ProjectDetail> projectDetails = projectDetailRepo.findAllByProject_ProjectIdAndStatus(projectId,status);
        if (projectDetails.isEmpty()) throw new CustomRuntimeExceptionv2(ExceptionCodev2.PROJECTDETAIL_NOT_FOUND);
        return projectDetails.stream()
                .map(projectDetail -> ProjectDetailResponse.builder()
                        .projectDetailId(projectDetail.getProjectDetailId())
                        .userName(projectDetail.getAccount().getUsername())
                        .status(projectDetail.getStatus())
                        .accountId(projectDetail.getAccount().getAccountId())
                        .joinDate(projectDetail.getJoinDate())
                        .build())
                .toList();
    }

    @Override
    public List<ProjectDetailResponse> updateProjectDetail(List<ProjectDetailRequest> projectDetailRequests) {
        projectDetailRequests.forEach(projectDetailRequest -> {
            ProjectDetail projectDetail = projectDetailRepo.findById(projectDetailRequest.getProjectDetailId())
                    .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.PROJECT_NOT_FOUND));
            // nếu nhân viện được duyệt thì set lại trạng thái trong dự án và ngày vào dự án
            if (projectDetailRequest.getStatus().equals("inproject")){
                projectDetail.setStatus(projectDetailRequest.getStatus());
                projectDetail.setJoinDate(new Date());
            } // nếu nhân viên bị từ chối tham gia cũng set trạng thái lại và xóa ngày vào dự án
            else if (projectDetailRequest.getStatus().equals("reject")){
                projectDetail.setStatus(projectDetailRequest.getStatus());
                projectDetail.setJoinDate(null);
            }
            projectDetailRepo.save(projectDetail);
        });
        return projectDetailRequests.stream()
                .map(projectDetailRequest -> ProjectDetailResponse.builder()
                        .projectDetailId(projectDetailRequest.getProjectDetailId())
                        .joinDate(projectDetailRequest.getJoinDate())
                        .status(projectDetailRequest.getStatus())
                        .build())
                .collect(Collectors.toList());
    }
}
