package com.example.trady.service;

import com.example.trady.entity.Buying;
import com.example.trady.entity.Member;

public interface BuyingService {

   // Buying createBuying(Long userId, Long productId, Long productOptionId);
    // userId 없이 구매 생성
   Buying createBuyingWithUser(Long productId, Long productOptionId, Member user) throws Exception;


}
