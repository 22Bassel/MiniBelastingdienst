package com.example.demo.database;

import com.example.demo.models.Entities.BelastingEntity;
import com.example.demo.models.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BelastingRepo extends JpaRepository<BelastingEntity,Long> {
    boolean existsByUserIdAndBelastingJaarAndBelastingsoort(Long userId, int belastingJaar,String belastingsoort);
}
