package com.example.trady.repository;

import com.example.trady.entity.Selling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellingRepository extends JpaRepository<Selling, Long> {
}
