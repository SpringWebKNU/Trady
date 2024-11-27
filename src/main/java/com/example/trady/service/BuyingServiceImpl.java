package com.example.trady.service;

import com.example.trady.entity.Buying;
import com.example.trady.entity.Member;
import com.example.trady.entity.Product;
import com.example.trady.entity.ProductOption;
import com.example.trady.repository.BuyingRepository;
import com.example.trady.repository.MemberRepository;
import com.example.trady.repository.ProductOptionRepository;
import com.example.trady.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class BuyingServiceImpl implements BuyingService {
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final BuyingRepository buyingRepository;

    public BuyingServiceImpl(MemberRepository memberRepository, ProductRepository productRepository, ProductOptionRepository productOptionRepository, BuyingRepository buyingRepository) {
        this.memberRepository = memberRepository;
        this.productRepository = productRepository;
        this.productOptionRepository = productOptionRepository;
        this.buyingRepository = buyingRepository;
    }
//
//    @Override
//    public Buying createBuying(Long userId, Long productId, Long productOptionId) {
//        // 유효성 검사
//        Member user = memberRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//        ProductOption productOption = productOptionRepository.findById(productOptionId)
//                .orElseThrow(() -> new RuntimeException("ProductOption not found"));
//
//        // 구매 정보 생성
//        Buying buying = new Buying(user, product, productOption);
//
//        // DB 저장
//        return buyingRepository.save(buying);
//    }


    // userId 없이 구매 생성
    @Override
    public Buying createBuyingWithoutUser(Long productId, Long productOptionId) throws Exception {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new Exception("Product not found"));
        ProductOption productOption = productOptionRepository.findById(productOptionId)
                .orElseThrow(() -> new Exception("ProductOption not found"));

        // userId 없이 구매를 생성할 수 있도록 수정
        Buying buying = new Buying();
        buying.setProduct(product);
        buying.setProductOption(productOption);

        return buyingRepository.save(buying);  // 구매 저장
    }
}
