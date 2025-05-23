package com.example.demo.Repository;

import com.example.demo.Entity.Soignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoignantRepository extends JpaRepository<Soignant, Long> {
}
