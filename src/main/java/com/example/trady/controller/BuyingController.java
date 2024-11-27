package com.example.trady.controller;

import com.example.trady.entity.Buying;
import com.example.trady.service.BuyingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BuyingController {

    @Autowired
    private BuyingService buyingService;

    @PostMapping("/buying/create")
    public String createBuying(
            @RequestParam(name = "productId") Long productId,
            @RequestParam(name = "productOptionId") Long productOptionId,
            Model model) {
        try {
            // userId 없이 구매 처리
            Buying buying = buyingService.createBuyingWithoutUser(productId, productOptionId);
            model.addAttribute("buying", buying);
            return "buying/success";  // 성공 페이지
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "buying/error";  // 오류 페이지
        }
    }

}
