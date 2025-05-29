package com.example.demo.Repository;

import com.example.demo.Entity.Soignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoignantRepository extends JpaRepository<Soignant, Long> {
    @Query("SELECT s FROM Soignant s LEFT JOIN FETCH s.patients")
    List<Soignant> findAllWithPatients();

}
