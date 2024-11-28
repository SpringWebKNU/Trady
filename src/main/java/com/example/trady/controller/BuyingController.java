package com.example.trady.controller;

import com.example.trady.entity.Buying;
import com.example.trady.entity.Member;
import com.example.trady.service.BuyingService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class BuyingController {

    @Autowired
    private BuyingService buyingService;

    @PostMapping("/buying/create")
    public String createBuying(
            @RequestParam(name = "productId") Long productId,
            @RequestParam(name = "productOptionId") Long productOptionId,
            HttpSession session, // 세션을 통해 로그인한 사용자 정보 가져오기
            Model model) {
        try {
            // 세션에서 로그인한 사용자 정보를 가져옴
            // 2. 세션에서 사용자 정보 가져오기
            Member member = (Member) session.getAttribute("currentUser");  // user -> member로 변경
            if (member == null) {
                log.error("로그인된 사용자가 없습니다.");
                return "redirect:/members/login";  // 로그인 페이지로 리다이렉트
            }

            // 로그인한 사용자와 함께 구매 생성
            Buying buying = buyingService.createBuyingWithUser(productId, productOptionId, member);  // user -> member로 변경
            model.addAttribute("buying", buying);
            return "buying/success";  // 성공 페이지
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "buying/error";  // 오류 페이지
        }
    }

}
