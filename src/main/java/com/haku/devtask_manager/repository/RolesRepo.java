package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepo extends JpaRepository<Roles,Long> {
    boolean existsByRolesName (String rolesName);
    Roles findOneByRolesName(String rolesName);
}
