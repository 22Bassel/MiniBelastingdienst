package com.example.demo.database;

import com.example.demo.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity,Long> {
    boolean existsByEmail(String email);
    UserEntity findByEmail(String email);
}
