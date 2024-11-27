package com.example.trady.controller;

import com.example.trady.dto.SellingForm;
import com.example.trady.entity.Product;
import com.example.trady.entity.ProductOption;
import com.example.trady.entity.Selling;
import com.example.trady.service.ProductService;
import com.example.trady.service.SellingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SellingController {

    @Autowired
    private SellingService sellingService;


    @Autowired
    private ProductService productService;


    // GET 요청을 보내는 경우
    @GetMapping("/products/{id}/sell")
    public String showSellPage(@PathVariable("id") Long productId, Model model) {
        // 상품 ID를 모델에 추가
        model.addAttribute("productId", productId);

        // 판매 폼 페이지 반환
        return "products/sell";  // sellForm.mustache로 렌더링됩니다.
    }

    // 상품 판매 폼 처리
    @PostMapping("/products/{id}/sell")
    public String sellProduct(@PathVariable("id") Long productId, @ModelAttribute SellingForm sellingForm) {
        // 1. 상품 정보 조회
        Product product = productService.findProductById(productId);

        // 2. SellingForm 데이터를 이용해 Selling 엔티티 생성
        Selling selling = sellingForm.toEntity();
        selling.setSproduct(product); // 상품 정보 설정

        // 3. 판매 정보 저장
       sellingService.createSelling(selling);



        // 4. 상품 상세 페이지로 리다이렉트
        return "redirect:/products/" + productId;
    }
}
