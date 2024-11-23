package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepo extends JpaRepository<Account,Long> {
    Account findOneByAccountId(Long accountId);
    Account findOneByUsername(String username);
    boolean existsByUsername(String username);
    List<Account> findAllByDepartmentDetailListIsNull();
}
