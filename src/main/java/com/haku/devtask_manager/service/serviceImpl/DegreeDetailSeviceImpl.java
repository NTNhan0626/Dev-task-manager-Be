package com.haku.devtask_manager.service.serviceImpl;

import com.haku.devtask_manager.entity.Account;
import com.haku.devtask_manager.entity.Degree;
import com.haku.devtask_manager.entity.DegreeDetail;
import com.haku.devtask_manager.exception.CustomRuntimeException;
import com.haku.devtask_manager.exception.CustomRuntimeExceptionv2;
import com.haku.devtask_manager.exception.ExceptionCode;
import com.haku.devtask_manager.exception.ExceptionCodev2;
import com.haku.devtask_manager.mapper.DegreeDetailMapper;
import com.haku.devtask_manager.payload.entityrequest.DegreeDetailRequest;
import com.haku.devtask_manager.payload.entityresponse.DegreeDetailResponse;
import com.haku.devtask_manager.repository.AccountRepo;
import com.haku.devtask_manager.repository.DegreeDetailRepo;
import com.haku.devtask_manager.repository.DegreeRepo;
import com.haku.devtask_manager.service.DegreeDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DegreeDetailSeviceImpl implements DegreeDetailService {
    private final DegreeDetailRepo degreeDetailRepo;
    private final DegreeDetailMapper degreeDetailMapper;
    private final AccountRepo accountRepo;
    private final DegreeRepo degreeRepo;
    @Override
    public DegreeDetailResponse createDegree(DegreeDetailRequest degreeRequest, Long degreeId, Long accountId) {
        DegreeDetail degreeDetail = degreeDetailMapper.toDegreeDetail(degreeRequest);
        Optional<Account> account = accountRepo.findById(accountId);
        degreeDetail.setAccount(account.orElseThrow(() -> new CustomRuntimeException(ExceptionCode.USER_NOT_FOUND.getCode(),ExceptionCode.USER_NOT_FOUND.getMessage())));
        Optional<Degree> degree = degreeRepo.findById(degreeId);
        degreeDetail.setDegree(degree.orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.DEGREE_NOT_FOUND)));

        degreeDetail.setStatus("Chờ duyệt");

        degreeDetailRepo.save(degreeDetail);
        return degreeDetailMapper.toDegreeDetailResponse(degreeDetail);
    }

    @Override
    public List<DegreeDetailResponse> getDegreeDetailByAccountId(Long accountId) {
       List<DegreeDetail> degreeDetails = degreeDetailRepo.findAllByAccount_AccountId(accountId);
       if (degreeDetails.isEmpty()){
           throw new CustomRuntimeExceptionv2(ExceptionCodev2.DEGREEDETAIL_NOT_FOUND);
       }
       return degreeDetails.stream().map(degreeDetail -> {
           DegreeDetailResponse degreeDetailResponse = degreeDetailMapper.toDegreeDetailResponse(degreeDetail);
           degreeDetailResponse.setDegreeName(degreeDetail.getDegree().getDegreeName());
           degreeDetailResponse.setMajor(degreeDetail.getDegree().getMajor());
           degreeDetailResponse.setAccountName(degreeDetail.getAccount().getUsername());
           degreeDetailResponse.setStatus(degreeDetail.getStatus());
           degreeDetailResponse.setDegreeId(degreeDetail.getDegree().getDegreeId());
           return degreeDetailResponse ;
       }).collect(Collectors.toList());
    }

    @Override
    public List<DegreeDetailResponse> getDegreeDetailByDegreeId(Long degreeId) {
        List<DegreeDetail> degreeDetails = degreeDetailRepo.findAllByDegree_DegreeId(degreeId);
        if (degreeDetails.isEmpty()){
            throw new CustomRuntimeExceptionv2(ExceptionCodev2.DEGREEDETAIL_NOT_FOUND);
        }
        return degreeDetails.stream().map(degreeDetail -> {
            DegreeDetailResponse degreeDetailResponse = degreeDetailMapper.toDegreeDetailResponse(degreeDetail);
            degreeDetailResponse.setDegreeName(degreeDetail.getDegree().getDegreeName());
            degreeDetailResponse.setMajor(degreeDetail.getDegree().getMajor());
            degreeDetailResponse.setAccountName(degreeDetail.getAccount().getUsername());
            degreeDetailResponse.setStatus(degreeDetail.getStatus());
            degreeDetailResponse.setDegreeId(degreeDetail.getDegree().getDegreeId());
            return degreeDetailResponse ;
        }).collect(Collectors.toList());
    }

    @Override
    public DegreeDetailResponse updateDegree(DegreeDetailRequest degreeRequest, Long degreeDetailId) {
        DegreeDetail degreeDetail = degreeDetailRepo.findById(degreeDetailId)
                .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.DEGREE_NOT_FOUND));
        if(degreeRequest.getIssueDate() != null) degreeDetail.setIssueDate(degreeRequest.getIssueDate());
        if(degreeRequest.getExpiryDate() != null) degreeDetail.setExpiryDate(degreeRequest.getExpiryDate());
        if(degreeRequest.getDuration() != 0) degreeDetail.setDuration(degreeRequest.getDuration());
        if(degreeRequest.getInstitution() != null) degreeDetail.setInstitution(degreeRequest.getInstitution());
        if(degreeRequest.getLevel() != null) degreeDetail.setLevel(degreeRequest.getLevel());
        if(degreeRequest.getStatus() != null) degreeDetail.setStatus(degreeRequest.getStatus());
        degreeDetailRepo.save(degreeDetail);
        return degreeDetailMapper.toDegreeDetailResponse(degreeDetail);
    }

    @Override
    public DegreeDetailResponse deleteDegreeDetail(Long degreeDetailId) {
        DegreeDetail degreeDetail = degreeDetailRepo.findById(degreeDetailId)
                .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.DEGREEDETAIL_NOT_FOUND));
        degreeDetailRepo.delete(degreeDetail);
        return degreeDetailMapper.toDegreeDetailResponse(degreeDetail);
    }
}
