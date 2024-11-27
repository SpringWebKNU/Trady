package com.example.trady.Service;

import com.example.trady.dto.ProductForm;
import com.example.trady.entity.Pcategory;
import com.example.trady.entity.Product;
import com.example.trady.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ProductService {

   Product saveProduct(ProductForm productForm);
   Product updateProduct(ProductForm productForm);
   Product findProductById(Long id);
   void deleteProduct(Long id);

   List<Product> search(String keyword);
   List<Product> findAllProducts();

   List<Pcategory> findAllCategories();
   Map<Long, List<Product>> groupProductsByCategory();

}
