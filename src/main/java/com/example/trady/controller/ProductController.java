package com.example.trady.controller;

import com.example.trady.entity.*;
import com.example.trady.service.*;
import com.example.trady.dto.ProductForm;
import com.example.trady.repository.PcategoryRepository;
import com.example.trady.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.text.DecimalFormat;
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
    ProductOptionService productOptionService;

    @Autowired
    SellingService sellingService;

    @Autowired
    BuyingService buyingService;

    @Autowired
    public ProductController(ProductOptionService productOptionService) {
        this.productOptionService = productOptionService;
    }

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

        // 각 제품에 대해 최저가 조회 및 포맷팅
        for (Product product : products) {
            // 각 제품에 대해 최저가를 찾음
            Long lowestPrice = productOptionService.findLowestPriceByProductId(product.getId());

            // 가격 포맷팅 (판매 정보가 없는 경우 "-" 표시)
            String formattedPrice = (lowestPrice != null)
                    ? new DecimalFormat("#,###").format(lowestPrice)
                    : "-";

            // 포맷된 가격을 모델에 추가 (각 제품마다)
            product.setFormattedPrice(formattedPrice);  // Product에 formattedPrice를 세팅

        }

        model.addAttribute("products", products);
        return "products/all";  // 제품 목록을 표시하는 뷰로 반환
    }

    @GetMapping("/products/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Product product = productService.findProductById(id);

        // 상품의 옵션들 가져오기
        List<ProductOption> productOptions = productService.getProductOptions(product.getId());

        // 판매 테이블에서 최저가 조회
        Long lowestPrice = productOptionService.findLowestPriceByProductId(id);

        // 가격 포맷팅 (판매 정보가 없는 경우 "-" 표시)
        String formattedPrice = (lowestPrice != null)
                ? new DecimalFormat("#,###").format(lowestPrice)
                : "-";

        // 포맷된 가격을 Product 객체에 세팅
        product.setFormattedPrice(formattedPrice);

        // 데이터베이스에 formattedPrice 값 업데이트
        productService.updateFormattedPrice(id, formattedPrice);

        // 모든 구매 완료된 상품 조회 (로그인 여부와 관계없이 모든 구매)
        List<Buying> allBuyings = buyingService.getAllBuyings();  // 이 메서드에서 모든 구매 정보를 조회

        // 모델에 product 추가
        model.addAttribute("product", product);
        model.addAttribute("productOptions", productOptions);
        model.addAttribute("buyings", allBuyings);  // 모든 구매 정보를 모델에 추가

        return "products/show";  // 상세 페이지로 반환
    }


    @GetMapping("/products/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        Product product = productService.findProductById(id);
        List<Pcategory> categories = productService.findAllCategories();

        // 카테고리 선택 여부를 설정
        categories.forEach(category -> {
            if (category.getId().equals(product.getPcategory().getId())) {
                // 선택된 카테고리 플래그 설정
                switch (category.getId().intValue()) {
                    case 1:
                        model.addAttribute("isCategory1", true);
                        break;
                    case 2:
                        model.addAttribute("isCategory2", true);
                        break;
                    case 3:
                        model.addAttribute("isCategory3", true);
                        break;
                    case 4:
                        model.addAttribute("isCategory4", true);
                        break;
                    case 5:
                        model.addAttribute("isCategory5", true);
                        break;
                    default:
                        break;
                }
            }
        });

        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "products/edit";
    }


    @PostMapping("/products/update")
    public String update(ProductForm productForm) {
        Product updatedProduct = productService.updateProduct(productForm);
        return "redirect:/products/" + updatedProduct.getId();
    }




    @GetMapping("/products/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes rttr) {
        productOptionService.deleteByProductId(id);
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

    @RequestMapping("/products/{productId}/buy")
    public String showProductPage(@PathVariable("productId") Long productId, Model model) {
        // 'product'를 모델에 추가
        Product product = productService.findProductById(productId);
        model.addAttribute("product", product); // 템플릿에 'product'를 전달

        // 'productOptions'도 모델에 추가
        List<ProductOption> productOptions = productOptionService.findByProductId(productId);
        model.addAttribute("productOptions", productOptions);

        return "products/buy";
    }

}