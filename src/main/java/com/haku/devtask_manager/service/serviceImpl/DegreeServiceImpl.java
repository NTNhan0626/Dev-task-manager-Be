package com.haku.devtask_manager.service.serviceImpl;

import com.haku.devtask_manager.entity.Degree;
import com.haku.devtask_manager.exception.CustomRuntimeExceptionv2;
import com.haku.devtask_manager.exception.ExceptionCodev2;
import com.haku.devtask_manager.mapper.DegreeMapper;
import com.haku.devtask_manager.payload.entityrequest.DegreeRequest;
import com.haku.devtask_manager.payload.entityresponse.DegreeResponse;
import com.haku.devtask_manager.repository.DegreeRepo;
import com.haku.devtask_manager.service.DegreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DegreeServiceImpl implements DegreeService {
    private final DegreeRepo degreeRepo;
    private final DegreeMapper degreeMapper;

    @Override
    public DegreeResponse createDegree(DegreeRequest degreeRequest) {
        Degree degree = degreeMapper.toDegree(degreeRequest);
        boolean checker = degreeRepo.existsByDegreeName(degreeRequest.getDegreeName());
        if(checker){
            throw new CustomRuntimeExceptionv2(ExceptionCodev2.DEGREE_EXISTS);
        }
        degreeRepo.save(degree);
        return degreeMapper.toDegreeResponse(degree);
    }

    @Override
    public List<DegreeResponse> getAllDegree() {
        List<Degree> degreeList = degreeRepo.findAll();
        if(degreeList.isEmpty()){
            throw new CustomRuntimeExceptionv2(ExceptionCodev2.DEGREE_NOT_FOUND);
        }
        return degreeMapper.toDegreeResponseList(degreeList);
    }

    @Override
    public DegreeResponse updateDegree(DegreeRequest degreeRequest, Long degreeId) {
        return null;
    }

    @Override
    public DegreeResponse deleteDegree(Long degreeId) {
        Degree degree = degreeRepo.findById(degreeId)
                .orElseThrow(() -> new CustomRuntimeExceptionv2(ExceptionCodev2.DEGREE_NOT_FOUND));
        degreeRepo.delete(degree);
        return degreeMapper.toDegreeResponse(degree);
    }
}
