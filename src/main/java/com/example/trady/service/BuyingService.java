package com.example.trady.service;

import com.example.trady.entity.Buying;

public interface BuyingService {

   // Buying createBuying(Long userId, Long productId, Long productOptionId);
    // userId 없이 구매 생성
    Buying createBuyingWithoutUser(Long productId, Long productOptionId) throws Exception;


}
