package com.haku.devtask_manager.mapper;

import com.haku.devtask_manager.entity.Specialization;
import com.haku.devtask_manager.entity.SpecializationDetail;
import com.haku.devtask_manager.payload.entityrequest.SpecializationDetailRequest;
import com.haku.devtask_manager.payload.entityrequest.SpecializationRequest;
import com.haku.devtask_manager.payload.entityresponse.SpecializationDetailResponse;
import com.haku.devtask_manager.payload.entityresponse.SpecializationResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpecializationDetailMapper {
    SpecializationDetail toSpecializationDetail(SpecializationDetailRequest specializationDetailRequest);
    SpecializationDetailResponse toSpecializationDetailResponse (SpecializationDetail specializationDetail);
    List<SpecializationDetailResponse> toSpecializationDetailResponses (List<SpecializationDetail> specializationDetails);
}
