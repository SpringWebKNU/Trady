package com.example.trady.controller;

import com.example.trady.service.PcategoryService;
import com.example.trady.service.ProductService;
import com.example.trady.dto.ProductForm;
import com.example.trady.entity.Pcategory;
import com.example.trady.entity.Product;
import com.example.trady.repository.PcategoryRepository;
import com.example.trady.repository.ProductRepository;
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
import java.util.Map;

@Slf4j
@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Autowired
    PcategoryService pcategoryService;

    @Autowired
    private PcategoryRepository pcategoryRepository;


    @GetMapping("/products/new")
    public String newProductForm(){
        return "products/new";
    }

    @PostMapping("/products/create")
    public String createProduct(ProductForm productForm) {
        log.info("Received ProductForm: {}", productForm);
        Product savedProduct = productService.saveProduct(productForm);
        return "redirect:/products/" + savedProduct.getId();
    }


    @GetMapping("/products/all")
    public String all(Model model) {
        List<Product> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "products/all";
    }


    // 상세 페이지
    // 데이터 조회 요청 접수
    @GetMapping("/products/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Product product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "products/show";
    }



    @GetMapping("/products/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        Product product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "products/edit";
    }

    @PostMapping("/products/update")
    public String update(ProductForm productForm) {
        Product updatedProduct = productService.updateProduct(productForm);
        return "redirect:/products/" + updatedProduct.getId();
    }




    @GetMapping("/products/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes rttr) {
        productService.deleteProduct(id);
        rttr.addFlashAttribute("msg", "삭제되었습니다.");
        return "redirect:/products/all";
    }


    @GetMapping("/products/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        log.info("Searching for products with keyword: {}", keyword);

        // ProductService를 통해 검색 결과 가져오기
        List<Product> products = productService.search(keyword);

        // 모델에 결과를 추가
        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);

        // 검색 결과 페이지로 포워딩
        return "products/searchResults";  // 결과를 보여줄 Mustache 템플릿 이름
    }


    @GetMapping("/products")
    public String listProducts(Model model) {
        // 1. 모든 카테고리와 제품을 가져오기 위해 서비스 호출
        List<Pcategory> categories = productService.findAllCategories();
        Map<Long, List<Product>> productsByCategory = productService.groupProductsByCategory();

        // 2. 첫 번째 카테고리를 활성 상태로 설정
        if (!categories.isEmpty()) {
            categories.get(0).setFirst(true);
        }

        // 3. 모델에 데이터 추가
        log.info("categories: {}", categories);
        log.info("productsByCategory: {}", productsByCategory);

        model.addAttribute("categories", categories);
        model.addAttribute("productsByCategory", productsByCategory);

        return "products/list";
    }



}