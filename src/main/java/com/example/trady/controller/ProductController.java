package com.example.trady.controller;

import com.example.trady.dto.ProductForm;
import com.example.trady.entity.Product;
import com.example.trady.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products/new")
    public String newProductForm(){
        return "products/new";
    }

    @PostMapping("/products/create")
    public String createProduct(ProductForm productform){

        productform.logInfo();
        Product product = productform.toEntity();
        product.logInfo();

        Product saved = productRepository.save(product);
        saved.logInfo();

        return "redirect:/products/"+saved.getId();
    }

    @GetMapping("/products/all")
    public String all(Model model){
        Iterable<Product> products = productRepository.findAll();

        // Add products to the model
        model.addAttribute("products", products);

        return "products/all";
    }

    // 상세 페이지
    // 데이터 조회 요청 접수
    @GetMapping("/products/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        log.info("id = {} ", id);

        Product product = productRepository.findById(id).orElse(null);
        model.addAttribute("product", product);

        return "products/show";
    }



    @GetMapping("/products/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model){

        Product productEntity = productRepository.findById(id).orElse(null);
        model.addAttribute("product", productEntity);

        return "products/edit";
    }

    @PostMapping("/products/update")
    public String update(ProductForm productForm){
        log.info(productForm.toString());

       Product productEntity = productForm.toEntity();
        log.info(productEntity.toString());

        Product target = productRepository.findById(productEntity.getId()).orElse(null);

        if(target != null){
            productRepository.save(productEntity);
        }
        return "redirect:/products/"+target.getId();
    }

    @GetMapping("/products/{id}/delete")
    public String delete(@PathVariable("id") Long id,
                         RedirectAttributes rttr){
        Product target = productRepository.findById(id).orElse(null);

        if(target != null){
            productRepository.deleteById(id);
            rttr.addFlashAttribute("msg","삭제되었습니다.");
        }

        return "redirect:/products/all";

    }

}
