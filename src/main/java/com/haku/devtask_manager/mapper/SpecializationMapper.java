package com.haku.devtask_manager.mapper;

import com.haku.devtask_manager.entity.Specialization;
import com.haku.devtask_manager.payload.entityrequest.SpecializationRequest;
import com.haku.devtask_manager.payload.entityresponse.SpecializationResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpecializationMapper {
    Specialization toSpecialization(SpecializationRequest specializationRequest);
    SpecializationResponse toSpecializationResponse (Specialization specialization);
    List<SpecializationResponse> toSpecializationResponseList (List<Specialization> specializationList);
}
