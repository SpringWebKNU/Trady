package com.example.trady.service;

import com.example.trady.dto.ProductForm;
import com.example.trady.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

   List<Product> search(String keyword);
   void saveProduct(ProductForm productForm);

}
