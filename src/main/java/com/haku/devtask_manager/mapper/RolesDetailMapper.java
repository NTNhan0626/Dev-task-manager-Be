package com.haku.devtask_manager.mapper;

import com.haku.devtask_manager.entity.RolesDetail;
import com.haku.devtask_manager.payload.entityrequest.RolesDetailRequest;
import com.haku.devtask_manager.payload.entityresponse.RolesDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolesDetailMapper {


    RolesDetail toRolesDetail(RolesDetailRequest rolesDetailRequest);


    RolesDetailResponse toRolesDetailResponse(RolesDetail rolesDetail);

    // Phương thức ánh xạ danh sách RolesDetail sang danh sách RolesDetailResponse
    List<RolesDetailResponse> toRolesDetailResponseList(List<RolesDetail> rolesDetailList);
}
