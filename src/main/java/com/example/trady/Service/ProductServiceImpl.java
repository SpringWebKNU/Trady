package com.example.trady.Service;

import com.example.trady.dto.ProductForm;
import com.example.trady.entity.Pcategory;
import com.example.trady.entity.Product;
import com.example.trady.repository.PcategoryRepository;
import com.example.trady.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PcategoryRepository pcategoryRepository;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, PcategoryRepository pcategoryRepository) {
        this.productRepository = productRepository;
        this.pcategoryRepository = pcategoryRepository;
    }

    @Override
    public Product saveProduct(ProductForm productForm) {
        // ProductForm에서 categoryId를 가져와서 Pcategory 엔티티를 조회
        Pcategory pcategory = pcategoryRepository.findById(productForm.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // ProductForm을 Product 엔티티로 변환하고, Pcategory를 설정
        Product product = productForm.toEntity(pcategory);

        // 변환된 Product 엔티티를 DB에 저장
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(ProductForm productForm) {
        // 1. ProductForm에서 카테고리를 가져옴
        Pcategory pcategory = pcategoryRepository.findById(productForm.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Invalid category ID: " + productForm.getCategoryId()));

        // 2. 기존 Product 엔티티 조회
        Product existingProduct = productRepository.findById(productForm.getId())
                .orElseThrow(() -> new RuntimeException("Product not found: " + productForm.getId()));

        // 3. Product 엔티티를 업데이트
        existingProduct.setPname(productForm.getPname());
        existingProduct.setPprice(productForm.getPprice());
        existingProduct.setPcategory(pcategory);

        // 4. 업데이트된 Product 저장
        return productRepository.save(existingProduct);
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Pcategory> findAllCategories() {
        return pcategoryRepository.findAll();
    }

    @Override
    public Map<Long, List<Product>> groupProductsByCategory() {
        List<Pcategory> categories = pcategoryRepository.findAll();
        Map<Long, List<Product>> productsByCategory = new HashMap<>();

        for (Pcategory category : categories) {
            List<Product> products = productRepository.findByPcategory(category);
            productsByCategory.put(category.getId(), products);
        }

        return productsByCategory;
    }


    @Override
    @Transactional
    public List<Product> search(String keyword) {
        return productRepository.findByPnameContaining(keyword);
    }
}
