package com.example.trady.service;

import com.example.trady.entity.Product;
import com.example.trady.entity.ProductOption;
import com.example.trady.repository.ProductOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductOptionServiceImpl implements ProductOptionService{

    private final ProductOptionRepository productOptionRepository;

    @Autowired
    public ProductOptionServiceImpl(ProductOptionRepository productOptionRepository) {
        this.productOptionRepository = productOptionRepository;
    }

    @Override
    public List<ProductOption> findByProductId(Long productId) {
        return productOptionRepository.findByProductId(productId);
    }

    @Override
    public ProductOption findById(Long id) {
        return productOptionRepository.findById(id).orElseThrow(() -> new RuntimeException("ProductOption not found"));
    }

    @Override
    public ProductOption save(ProductOption productOption) {
        return productOptionRepository.save(productOption);
    }

    @Override
    public void deleteByProductId(Long productId) {
        // 해당 productId에 속하는 모든 ProductOption을 찾아 삭제
        List<ProductOption> options = productOptionRepository.findByProductId(productId);
        for (ProductOption option : options) {
            productOptionRepository.delete(option);
        }
    }

    @Override
    public void createProductOption(Product product, String size, int highPrice, int lowPrice) {
        ProductOption productOption = new ProductOption();
        productOption.setProduct(product);  // 상품 설정
        productOption.setSize(size);  // 사이즈 설정
        productOption.setHighPrice(highPrice);  // 높은 가격 설정
        productOption.setLowPrice(lowPrice);  // 낮은 가격 설정

        // 상품 옵션 저장
        productOptionRepository.save(productOption);
    }

}
