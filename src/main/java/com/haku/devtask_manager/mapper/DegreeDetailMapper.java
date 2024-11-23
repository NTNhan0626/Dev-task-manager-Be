package com.haku.devtask_manager.mapper;

import com.haku.devtask_manager.entity.DegreeDetail;
import com.haku.devtask_manager.payload.entityrequest.DegreeDetailRequest;
import com.haku.devtask_manager.payload.entityresponse.DegreeDetailResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DegreeDetailMapper {
    DegreeDetail toDegreeDetail (DegreeDetailRequest degreeDetailRequest);
    DegreeDetailResponse toDegreeDetailResponse (DegreeDetail degreeDetail);
    List<DegreeDetailResponse> toDegreeDetailResponses (List<DegreeDetail> degreeDetails);
}
