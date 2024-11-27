package com.example.trady.service;

import com.example.trady.dto.ProductForm;
import com.example.trady.entity.Pcategory;
import com.example.trady.entity.Product;
import com.example.trady.repository.PcategoryRepository;
import com.example.trady.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PcategoryRepository pcategoryRepository;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, PcategoryRepository pcategoryRepository) {
        this.productRepository = productRepository;
        this.pcategoryRepository = pcategoryRepository;
    }

    @Override
    public void saveProduct(ProductForm productForm) {
        // ProductForm에서 categoryId를 가져와서 Pcategory 엔티티를 조회
        Pcategory pcategory = pcategoryRepository.findById(productForm.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // ProductForm을 Product 엔티티로 변환하고, Pcategory를 설정
        Product product = productForm.toEntity(pcategory);

        // 변환된 Product 엔티티를 DB에 저장
        productRepository.save(product);
    }

    @Override
    @Transactional
    public List<Product> search(String keyword) {
        return productRepository.findByPnameContaining(keyword);
    }
}
