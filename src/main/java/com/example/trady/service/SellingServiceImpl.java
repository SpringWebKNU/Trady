package com.example.trady.service;

import com.example.trady.entity.Member;
import com.example.trady.entity.Product;
import com.example.trady.entity.Selling;
import com.example.trady.repository.ProductOptionRepository;
import com.example.trady.repository.ProductRepository;
import com.example.trady.repository.SellingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellingServiceImpl implements SellingService {

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
    public Selling createSelling(Selling selling, Product product, String size, int price) {
        // 1. 판매 정보를 저장
        Selling savedSelling = sellingRepository.save(selling);

        // 2. 상품 옵션 저장: size와 price 값을 이용해 상품 옵션을 생성
        // 제품에 대한 옵션을 생성하고 저장
        productOptionService.createProductOption(product, size, price);

        // 3. 저장된 Selling 객체 반환
        return savedSelling;
    }




    @Override
    public List<Selling> findAllByUser(Member user) {
        return sellingRepository.findByUser(user);
    }
}