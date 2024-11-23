package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.DegreeDetail;
import com.haku.devtask_manager.entity.Specialization;
import com.haku.devtask_manager.entity.SpecializationDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecializationDetailRepo extends JpaRepository<SpecializationDetail,Long> {
    List<SpecializationDetail> findAllByAccount_AccountId(Long accountId);
    List<SpecializationDetail> findAllBySpecialization_SpecializationId(Long specializationId);
    SpecializationDetail findOneByAccount_AccountIdAndSpecialization_SpecializationId(Long accountId,Long specializationId);
}
