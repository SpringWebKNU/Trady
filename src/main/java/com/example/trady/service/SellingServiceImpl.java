package com.example.trady.service;

import com.example.trady.dto.SellingForm;
import com.example.trady.entity.Product;
import com.example.trady.entity.ProductOption;
import com.example.trady.entity.Selling;
import com.example.trady.repository.ProductOptionRepository;
import com.example.trady.repository.ProductRepository;
import com.example.trady.repository.SellingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellingServiceImpl implements SellingService{

    @Autowired
    private SellingRepository sellingRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductOptionRepository productOptionRepository;

    @Autowired
    private ProductOptionService productOptionService;

    @Override
    public Selling createSelling(Selling selling) {
        // 판매 정보를 저장
        return sellingRepository.save(selling);
    }


    @Override
    public Selling createSelling(Selling selling, Product product, String size, int highPrice, int lowPrice) {
        // 판매 정보를 저장
        Selling savedSelling = sellingRepository.save(selling);

        // 상품 옵션 저장
        productOptionService.createProductOption(product, size, highPrice, lowPrice);

        // 저장된 Selling 객체를 반환
        return savedSelling;
    }

}
