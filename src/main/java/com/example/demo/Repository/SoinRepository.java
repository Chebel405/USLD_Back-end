package com.example.demo.Repository;

import com.example.demo.Entity.Soin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoinRepository extends JpaRepository<Soin, Long> {
}
