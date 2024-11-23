package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepo extends JpaRepository<Information,Long> {
    Information findOneByAccount_AccountId(Long accountId);
}
