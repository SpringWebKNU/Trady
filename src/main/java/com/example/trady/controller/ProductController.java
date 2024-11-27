package com.example.trady.controller;

import com.example.trady.Service.PcategoryService;
import com.example.trady.Service.PcategoryServiceImpl;
import com.example.trady.Service.ProductService;
import com.example.trady.dto.ProductForm;
import com.example.trady.entity.Pcategory;
import com.example.trady.entity.Product;
import com.example.trady.repository.PcategoryRepository;
import com.example.trady.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
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

        if (productForm.getCategoryId() == null) {
            throw new IllegalArgumentException("Category ID must not be null.");
        }

        Pcategory pcategory = pcategoryService.findById(productForm.getCategoryId());
        if (pcategory == null) {
            throw new IllegalArgumentException("Invalid category ID: " + productForm.getCategoryId());
        }

        Product product = productForm.toEntity(pcategory);
        product.logInfo();

        Product saved = productRepository.save(product);
        saved.logInfo();

        return "redirect:/products/" + saved.getId();
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


        // Pcategory를 가져오기 위해 서비스 호출
        Pcategory pcategory = pcategoryService.findById(productForm.getCategoryId());
        if (pcategory == null) {
            throw new IllegalArgumentException("Invalid category ID: " + productForm.getCategoryId());
        }

       Product productEntity = productForm.toEntity(pcategory);
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
        List<Pcategory> categories = pcategoryRepository.findAll();  // Get all categories
        Map<Long, List<Product>> productsByCategory = new HashMap<>();

        // For each category, find all products belonging to that category
        for (Pcategory category : categories) {
            List<Product> products = productRepository.findByPcategory(category); // Assuming this method exists
            productsByCategory.put(category.getId(), products);
        }

        if (!categories.isEmpty()) {
            categories.get(0).setFirst(true);  // Set the first category as active
        }

        log.info("categories: {}", categories);
        log.info("productsByCategory: {}", productsByCategory);

        model.addAttribute("categories", categories);
        model.addAttribute("productsByCategory", productsByCategory);  // Pass the map of products grouped by category
        return "products/list";  // Return the Mustache template
    }





}
