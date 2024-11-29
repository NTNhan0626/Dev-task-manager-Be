package com.haku.devtask_manager.service.serviceImpl;

import com.haku.devtask_manager.entity.Account;
import com.haku.devtask_manager.entity.ProjectDetail;
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
                        .build())
                .collect(Collectors.toList());
    }
}
