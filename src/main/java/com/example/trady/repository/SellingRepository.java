package com.example.trady.repository;

import com.example.trady.entity.Member;
import com.example.trady.entity.Selling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellingRepository extends JpaRepository<Selling, Long> {

    // 사용자별 판매 상품 조회 쿼리
    List<Selling> findByUser(Member user);
}