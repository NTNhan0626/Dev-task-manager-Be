package com.haku.devtask_manager.mapper;

import com.haku.devtask_manager.entity.Degree;
import com.haku.devtask_manager.payload.entityrequest.DegreeRequest;
import com.haku.devtask_manager.payload.entityresponse.DegreeResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DegreeMapper {
    Degree toDegree (DegreeRequest degreeRequest);
    DegreeResponse toDegreeResponse (Degree degree);
    List<DegreeResponse> toDegreeResponseList (List<Degree> degreeList);
}
