package com.haku.devtask_manager.service.serviceImpl;

import com.haku.devtask_manager.entity.Specialization;
import com.haku.devtask_manager.exception.CustomRuntimeExceptionv2;
import com.haku.devtask_manager.exception.ExceptionCodev2;
import com.haku.devtask_manager.mapper.SpecializationMapper;
import com.haku.devtask_manager.payload.entityrequest.SpecializationRequest;
import com.haku.devtask_manager.payload.entityresponse.SpecializationResponse;
import com.haku.devtask_manager.repository.SpecializationRepo;
import com.haku.devtask_manager.service.SpecializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpecializationServiceImpl implements SpecializationService {
    private final SpecializationMapper specializationMapper;
    private final SpecializationRepo specializationRepo;

    @Override
    public SpecializationResponse createSpecialization(SpecializationRequest specializationRequest) {
        Specialization specialization = specializationMapper.toSpecialization(specializationRequest);
        boolean checker = specializationRepo.existsBySpecializationName(specializationRequest.getSpecializationName());
        if(checker){
            throw new CustomRuntimeExceptionv2(ExceptionCodev2.SPECIALIZATION_EXISTS);
        }
        specializationRepo.save(specialization);
        return specializationMapper.toSpecializationResponse(specialization);
    }

    @Override
    public List<SpecializationResponse> getAllSpecialization() {
        List<Specialization> specializationResponses = specializationRepo.findAll();
        if (specializationResponses.isEmpty()){
            throw new CustomRuntimeExceptionv2(ExceptionCodev2.SPECIALIZATION_NOT_FOUND);
        }
        return specializationMapper.toSpecializationResponseList(specializationResponses);
    }

    @Override
    public SpecializationResponse updateSpecialization(SpecializationRequest specializationRequest, Long specializationId) {
        Specialization specialization = specializationRepo.findById(specializationId)
                .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.SPECIALIZATION_NOT_FOUND));
        specialization.setSpecializationName(specializationRequest.getSpecializationName());

        specializationRepo.save(specialization);
        return specializationMapper.toSpecializationResponse(specialization);
    }

    @Override
    public SpecializationResponse deleteSpecialization(Long specializationId) {
        Specialization specialization = specializationRepo.findById(specializationId)
                .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.SPECIALIZATION_NOT_FOUND));
        specializationRepo.delete(specialization);
        return specializationMapper.toSpecializationResponse(specialization);
    }
}
