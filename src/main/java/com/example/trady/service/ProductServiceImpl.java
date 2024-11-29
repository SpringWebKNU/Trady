package com.example.trady.service;

import com.example.trady.dto.ProductForm;
import com.example.trady.entity.Pcategory;
import com.example.trady.entity.Product;
import com.example.trady.repository.PcategoryRepository;
import com.example.trady.repository.ProductRepository;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PcategoryRepository pcategoryRepository;
    @Autowired
    private ServletContext servletContext;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, PcategoryRepository pcategoryRepository) {
        this.productRepository = productRepository;
        this.pcategoryRepository = pcategoryRepository;
    }

    @Override
    public Product saveProduct(ProductForm productForm) {
        Pcategory pcategory = pcategoryRepository.findById(productForm.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // 파일 처리
        String filePath = saveFile(productForm.getPimg()); // MultipartFile을 처리

        // 가격 포맷팅: 서버에서 가격을 원화 형식으로 포맷
        DecimalFormat formatter = new DecimalFormat("#,###");
        String formattedPrice = formatter.format(productForm.getPprice()); // 가격 포맷팅

        // ProductForm을 Product 엔티티로 변환하고, 파일 경로를 설정
        Product product = productForm.toEntity(pcategory, filePath); // filePath와 pdate를 포함


        // 포맷팅된 가격을 엔티티에 설정
        product.setPprice(Long.parseLong(formattedPrice)); // 가격을 포맷팅된 값으로 설정

        // pdate가 null인 경우 현재 시간으로 설정
        if (product.getPdate() == null) {
            product.setPdate(LocalDateTime.now()); // pdate에 현재 시간 설정
        }

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(ProductForm productForm) {
        Pcategory pcategory = pcategoryRepository.findById(productForm.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Invalid category ID: " + productForm.getCategoryId()));

        Product existingProduct = productRepository.findById(productForm.getId())
                .orElseThrow(() -> new RuntimeException("Product not found: " + productForm.getId()));

        // 파일 처리
        String filePath = saveFile(productForm.getPimg()); // MultipartFile을 처리

        // 기존 Product 엔티티 업데이트
        existingProduct.setPname(productForm.getPname());
        existingProduct.setPprice(productForm.getPprice());
        existingProduct.setPcategory(pcategory);

        // pdate가 null인 경우, 이전의 pdate를 그대로 유지하거나 현재 시간을 설정
        if (productForm.getPdate() != null) {
            existingProduct.setPdate(productForm.getPdate());
        } else {
            existingProduct.setPdate(LocalDateTime.now()); // pdate가 null이면 현재 시간으로 설정
        }

        // 새로운 파일이 업로드된 경우에만 이미지 경로 업데이트
        if (filePath != null) {
            existingProduct.setPimg(filePath);
        }

        return productRepository.save(existingProduct);
    }

    public String saveFile(MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            // 업로드 디렉토리를 "C:/Trady/uploads"로 설정
            String uploadDir = "C:/Trady/src/main/resources/static/images/uploads";  // 절대 경로 설정

            // 파일 이름에 고유한 UUID를 추가하여 충돌 방지
            String fileName = UUID.randomUUID().toString().substring(0, 8) + "_" + file.getOriginalFilename();

            // 업로드 디렉토리와 파일 이름을 결합하여 최종 파일 경로 생성
            File destinationFile = new File(uploadDir, fileName);

            // 디렉토리가 없다면 생성
            if (!destinationFile.getParentFile().exists()) {
                destinationFile.getParentFile().mkdirs();  // 디렉토리 생성
            }

            try {
                // 파일을 해당 경로에 저장
                file.transferTo(destinationFile);

                // 상대 경로로 반환 (웹 애플리케이션에서 접근 가능한 경로)
                return fileName;
            } catch (IOException e) {
                throw new RuntimeException("File upload failed", e);
            }
        }
        return null;
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