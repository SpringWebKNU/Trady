package com.example.trady.service;

import com.example.trady.entity.Buying;
import com.example.trady.entity.Member;

import java.util.List;

public interface BuyingService {

   // Buying createBuying(Long userId, Long productId, Long productOptionId);
    // userId 없이 구매 생성
   Buying createBuyingWithUser(Long productId, Long productOptionId, Member user) throws Exception;

   List<Buying> getPurchasesByProduct(Long productId);

}
