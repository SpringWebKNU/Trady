package com.example.trady.controller;

import com.example.trady.dto.SellingForm;
import com.example.trady.entity.Member;
import com.example.trady.entity.Product;
import com.example.trady.entity.ProductOption;
import com.example.trady.entity.Selling;
import com.example.trady.service.MemberService;
import com.example.trady.service.ProductOptionService;
import com.example.trady.service.ProductService;
import com.example.trady.service.SellingService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class SellingController {

    @Autowired
    private SellingService sellingService;


    @Autowired
    private ProductService productService;

    @Autowired
    private ProductOptionService productOptionService;


    @Autowired
    private MemberService memberService;  // MemberService 사용

    // GET 요청을 보내는 경우
    @GetMapping("/products/{id}/sell")
    public String showSellPage(@PathVariable("id") Long productId, Model model) {
        // 1. 상품 정보 조회
        Product product = productService.findProductById(productId);

        // 2. 해당 상품에 대한 모든 옵션 조회
        List<ProductOption> productOptions = productOptionService.findByProduct(product);

        // 3. 모델에 상품 정보와 옵션 리스트를 추가
        model.addAttribute("product", product);
        model.addAttribute("productOptions", productOptions);  // 사이즈 옵션들 전달
        model.addAttribute("productId", productId);  // productId 추가

        // 4. 판매 폼 페이지 반환
        return "products/sell";  // sellForm.mustache로 렌더링됩니다.
    }

    // 상품 판매 폼 처리

    @PostMapping("/products/{id}/sell")
    public String sellProduct(@PathVariable("id") Long productId,
                              @ModelAttribute SellingForm sellingForm,
                              HttpSession session) {

        // 1. 상품 정보 조회
        Product product = productService.findProductById(productId);

        // 2. 세션에서 사용자 정보 가져오기
        Member member = (Member) session.getAttribute("currentUser");
        if (member == null) {
            log.error("로그인된 사용자가 없습니다.");
            return "redirect:/members/login";  // 로그인 페이지로 리다이렉트
        }

        // 3. SellingForm 데이터를 이용해 Selling 엔티티 생성
        Selling selling = sellingForm.toEntity();
        selling.setSproduct(product);  // 상품 정보 설정
        selling.setUser(member);  // 로그인한 사용자 설정

        // 로그를 추가하여 값 확인
        log.info("Selling 정보: {}", selling);
        log.info("로그인한 사용자: {}", member);

        // 4. 판매 정보 저장 및 상품 옵션 생성
        sellingService.createSelling(selling, product, sellingForm.getSize(), sellingForm.getSprice());

        // 5. 상품 상세 페이지로 리다이렉트
        return "redirect:/products/" + productId;
    }


}
