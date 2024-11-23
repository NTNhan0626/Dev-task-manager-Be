package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.Degree;
import com.haku.devtask_manager.entity.DegreeDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DegreeDetailRepo extends JpaRepository<DegreeDetail,Long> {
    List<DegreeDetail> findAllByAccount_AccountId(Long accountId);
    List<DegreeDetail> findAllByDegree_DegreeId(Long degreeId);
    DegreeDetail findOneByAccount_AccountIdAndDegree_DegreeId(Long accountId,Long degreeId);
}
