package com.haku.devtask_manager.service.serviceImpl;

import com.haku.devtask_manager.entity.Account;
import com.haku.devtask_manager.entity.Specialization;
import com.haku.devtask_manager.entity.SpecializationDetail;
import com.haku.devtask_manager.exception.CustomRuntimeException;
import com.haku.devtask_manager.exception.CustomRuntimeExceptionv2;
import com.haku.devtask_manager.exception.ExceptionCode;
import com.haku.devtask_manager.exception.ExceptionCodev2;
import com.haku.devtask_manager.mapper.SpecializationDetailMapper;
import com.haku.devtask_manager.payload.entityrequest.SpecializationDetailRequest;
import com.haku.devtask_manager.payload.entityresponse.SpecializationDetailResponse;
import com.haku.devtask_manager.repository.AccountRepo;
import com.haku.devtask_manager.repository.SpecializationDetailRepo;
import com.haku.devtask_manager.repository.SpecializationRepo;
import com.haku.devtask_manager.service.SpecializationDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecializationDetailServiceImpl implements SpecializationDetailService {

    private final SpecializationDetailRepo specializationDetailRepo;
    private final SpecializationDetailMapper specializationDetailMapper;
    private final AccountRepo accountRepo;
    private final SpecializationRepo specializationRepo;

    @Override
    public SpecializationDetailResponse createSpecializationDetail(SpecializationDetailRequest request) {
        Account account = accountRepo.findById(request.getAccountId())
                .orElseThrow(() -> new CustomRuntimeException(ExceptionCode.USER_NOT_FOUND.getCode(),ExceptionCode.USER_NOT_FOUND.getMessage()));
        Specialization specialization = specializationRepo.findById(request.getSpecializationId())
                .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.SPECIALIZATION_NOT_FOUND));

        SpecializationDetail specializationDetail = specializationDetailMapper.toSpecializationDetail(request);
        specializationDetail.setSpecialization(specialization);
        specializationDetail.setAccount(account);
        SpecializationDetail savedDetail = specializationDetailRepo.save(specializationDetail);
        return specializationDetailMapper.toSpecializationDetailResponse(savedDetail);
    }

    @Override
    public SpecializationDetailResponse updateSpecializationDetail(Long id, SpecializationDetailRequest request) {
        SpecializationDetail specializationDetail = specializationDetailRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Specialization Detail not found"));
        if(request.getLevel() !=null) specializationDetail.setLevel(request.getLevel());
        if(request.getStartDate() !=null) specializationDetail.setStartDate(request.getStartDate());
        if(request.getDescription() !=null) specializationDetail.setDescription(request.getDescription());
        if(request.getStatus() !=null) specializationDetail.setStatus(request.getStatus());

        SpecializationDetail updatedDetail = specializationDetailRepo.save(specializationDetail);
        return specializationDetailMapper.toSpecializationDetailResponse(updatedDetail);
    }

    @Override
    public List<SpecializationDetailResponse> getSpecializationDetailByAccountId(Long accountId) {
        List<SpecializationDetail> specializationDetailResponses = specializationDetailRepo.findAllByAccount_AccountId(accountId);
        if (specializationDetailResponses.isEmpty()){
            throw new CustomRuntimeExceptionv2(ExceptionCodev2.SPECIALIZATION_NOT_FOUND);
        }
        return specializationDetailResponses.stream()
                .map(specializationDetail -> {
                    SpecializationDetailResponse specializationDetailResponse = specializationDetailMapper.toSpecializationDetailResponse(specializationDetail);
                    specializationDetailResponse.setSpecializationName(specializationDetail.getSpecialization().getSpecializationName());
                    specializationDetailResponse.setAccountName(specializationDetail.getAccount().getUsername());
                    specializationDetailResponse.setStatus(specializationDetail.getStatus());
                    specializationDetailResponse.setSpecializationId(specializationDetail.getSpecialization().getSpecializationId());
                    return specializationDetailResponse;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<SpecializationDetailResponse> getSpecializationDetailBySpecializationId(Long specializationId) {
        List<SpecializationDetail> specializationDetailResponses = specializationDetailRepo.findAllBySpecialization_SpecializationId(specializationId);
        if (specializationDetailResponses.isEmpty()){
            throw new CustomRuntimeExceptionv2(ExceptionCodev2.SPECIALIZATION_NOT_FOUND);
        }
        return specializationDetailResponses.stream()
                .map(specializationDetail -> {
                    SpecializationDetailResponse specializationDetailResponse = specializationDetailMapper.toSpecializationDetailResponse(specializationDetail);
                    specializationDetailResponse.setSpecializationName(specializationDetail.getSpecialization().getSpecializationName());
                    specializationDetailResponse.setAccountName(specializationDetail.getAccount().getUsername());
                    specializationDetailResponse.setStatus(specializationDetail.getStatus());
                    specializationDetailResponse.setSpecializationId(specializationDetail.getSpecialization().getSpecializationId());
                    return specializationDetailResponse;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSpecializationDetail(Long id) {
        SpecializationDetail specializationDetail = specializationDetailRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Specialization Detail not found"));
        specializationDetailRepo.delete(specializationDetail);
    }

    @Override
    public List<SpecializationDetailResponse> getAllSpecializationDetails() {
        return specializationDetailRepo.findAll().stream()
                .map(specializationDetailMapper::toSpecializationDetailResponse)
                .collect(Collectors.toList());
    }
}
