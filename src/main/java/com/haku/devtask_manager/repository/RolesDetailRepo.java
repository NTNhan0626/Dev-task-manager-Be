package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.Account;
import com.haku.devtask_manager.entity.Roles;
import com.haku.devtask_manager.entity.RolesDetail;
import com.haku.devtask_manager.payload.entityresponse.AccountResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolesDetailRepo extends JpaRepository<RolesDetail,Long> {
    boolean existsByAccount_AccountIdAndRoles_RolesId(Long accountId, Long rolesId);
    List<RolesDetail> findAllRolesDetailByRoles(Roles roles);
    RolesDetail findOneByAccount_AccountIdAndRoles_RolesId(Long accountId,Long rolesId);
    void deleteByAccountAndRoles(Account account,Roles roles);
    List<RolesDetail> findAllByAccountInAndRoles(List<Account> accounts,Roles roles );
}
