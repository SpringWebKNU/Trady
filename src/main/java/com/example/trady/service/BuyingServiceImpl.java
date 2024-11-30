package com.example.trady.service;

import com.example.trady.entity.Buying;
import com.example.trady.entity.Member;
import com.example.trady.entity.Product;
import com.example.trady.entity.ProductOption;
import com.example.trady.repository.BuyingRepository;
import com.example.trady.repository.ProductOptionRepository;
import com.example.trady.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BuyingServiceImpl implements BuyingService {

    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final BuyingRepository buyingRepository;

    public BuyingServiceImpl(ProductRepository productRepository,
                             ProductOptionRepository productOptionRepository,
                             BuyingRepository buyingRepository) {
        this.productRepository = productRepository;
        this.productOptionRepository = productOptionRepository;
        this.buyingRepository = buyingRepository;
    }

    @Transactional
    public Buying createBuyingWithUser(Long productId, Long productOptionId, Member user) throws Exception {
        // 상품과 상품 옵션 조회
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new Exception("Product not found"));

        ProductOption productOption = productOptionRepository.findById(productOptionId)
                .orElseThrow(() -> new Exception("ProductOption not found"));

        // Buying 엔티티 생성 (user 정보 추가)
        Buying buying = new Buying();
        buying.setUser(user);  // 로그인한 사용자 정보 설정
        buying.setProduct(product);
        buying.setProductOption(productOption);

        buying.setSize(productOption.getSize());  // size 값 설정
        buying.setPrice(productOption.getPrice());  // price 값 설정


        // Buying 저장
        Buying savedBuying = buyingRepository.save(buying);

        // BUYING 테이블에서 product_option_id를 다른 값으로 업데이트 (NULL을 피하기 위해)
        buyingRepository.updateProductOptionToDefault(productOptionId);

        // ProductOption 삭제
        productOptionRepository.deleteById(productOptionId);  // ProductOption 삭제

        return savedBuying;
    }


    public List<Buying> getPurchasesByProduct(Long productId) {
        return buyingRepository.findByProductId(productId);
    }

    public List<Buying> getAllBuyings() {
        // 모든 구매 정보를 조회하여 반환
        return buyingRepository.findAll();
    }

}
