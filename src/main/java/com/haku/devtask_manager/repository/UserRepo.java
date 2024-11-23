package com.haku.devtask_manager.repository;

import com.haku.devtask_manager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,String> {
    boolean existsByUserName(String username);
    User findByUserName(String username);
}
