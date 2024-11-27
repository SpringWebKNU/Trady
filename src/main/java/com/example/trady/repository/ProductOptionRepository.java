package com.example.trady.repository;

import com.example.trady.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {
    // 특정 상품에 속한 옵션들을 가져오는 메서드
    List<ProductOption> findByProductId(Long productId);
}
