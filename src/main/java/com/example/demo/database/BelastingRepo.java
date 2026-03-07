package com.example.demo.database;

import com.example.demo.models.entities.BelastingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BelastingRepo extends JpaRepository<BelastingEntity,Long> {
    boolean existsByUserIdAndBelastingJaarAndBelastingsoort(Long userId, int belastingJaar,String belastingsoort);
}
