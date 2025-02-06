package com.haku.devtask_manager.payload.entityresponse;

import com.haku.devtask_manager.entity.Degree;
import com.haku.devtask_manager.entity.DepartmentDetail;
import com.haku.devtask_manager.entity.Information;
import com.haku.devtask_manager.entity.RolesDetail;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountResponse {
    private Long accountId;
    private String username;
    private String email;
    private String status;
    private Date dateCreate;

    private InformationResponse informationResponse;

    private String position; // vai trò trong phòng
    private String specializations; // các chuyên môn cá nhân
    private String statusProject; // trạng thái có dang làm dự án nào nay không
    private List<DegreeDetailResponse> detailResponses;
    private List<SpecializationDetailResponse> specializationDetailResponses;
    private String statusInProject; // trạng thái của tài khoản trong 1 dự án


}
