package com.example.trady.service;

import com.example.trady.entity.Product;
import com.example.trady.entity.ProductOption;

import java.util.List;

public interface ProductOptionService {

    List<ProductOption> findByProductId(Long productId);

    ProductOption findById(Long id);

    ProductOption save(ProductOption productOption);

    void deleteByProductId(Long productId);  // 특정 Product의 모든 옵션 삭제

    void createProductOption(Product product, String size, int highPrice, int lowPrice);
}
