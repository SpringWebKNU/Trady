package com.example.trady.controller;

import com.example.trady.entity.Buying;
import com.example.trady.entity.Member;
import com.example.trady.entity.Product;
import com.example.trady.entity.ProductOption;
import com.example.trady.service.BuyingService;
import com.example.trady.service.ProductOptionService;
import com.example.trady.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
public class BuyingController {

    @Autowired
    private BuyingService buyingService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductOptionService productOptionService;


    @GetMapping("/products/{productId}/buy")
    public String showProductPage(@PathVariable("productId") Long productId, Model model) {
        // 상품 정보 조회
        Product product = productService.findProductById(productId);
        model.addAttribute("product", product);

        // 상품 옵션(사이즈와 가격 정보) 조회
        List<ProductOption> productOptions = productOptionService.findByProductId(productId);
        model.addAttribute("productOptions", productOptions);

        return "products/buy";
    }


    @PostMapping("/buying/create")
    public String createBuying(
            @RequestParam(name = "productId") Long productId,
            @RequestParam(name = "productOptionId") Long productOptionId,
            HttpSession session, // 세션을 통해 로그인한 사용자 정보 가져오기
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            // 세션에서 로그인한 사용자 정보를 가져옴
            Member member = (Member) session.getAttribute("currentUser");
            if (member == null) {
                log.error("로그인된 사용자가 없습니다.");
                redirectAttributes.addFlashAttribute("msg", "로그인된 사용자가 없습니다. 로그인 해주세요.");
                return "redirect:/members/login";  // 로그인 페이지로 리다이렉트
            }

            // 구매 생성
            Buying buying = buyingService.createBuyingWithUser(productId, productOptionId, member);

            // 사용자 정보와 구매 정보 전달
            model.addAttribute("userName", member.getUsername());
            model.addAttribute("email", member.getEmail());
            model.addAttribute("address", member.getAddr());
            model.addAttribute("productName", buying.getProduct().getPname());  // 선택된 상품 이름
            model.addAttribute("size", buying.getProductOption().getSize());  // 선택된 사이즈
            model.addAttribute("price", buying.getProductOption().getPrice());  // 선택된 사이즈
            model.addAttribute("buying", buying);
            // 상품 이미지 URL 추가
            model.addAttribute("productImageUrl", buying.getProduct().getPimg());  // 상품 이미지 URL


            return "buying/success";  // 성공 페이지
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "buying/error";  // 오류 페이지
        }
    }

    @GetMapping("/buying/success")
    public String orderSuccess(Model model) {
        // 이 부분은 실제 POST 요청에서 데이터를 받아서 처리할 필요 있음.
        return "/buying/success";  // success 템플릿을 렌더링
    }



}
