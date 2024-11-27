package com.example.trady.service;

import com.example.trady.dto.SellingForm;
import com.example.trady.entity.Product;
import com.example.trady.entity.ProductOption;
import com.example.trady.entity.Selling;

public interface SellingService {
    Selling createSelling(Selling selling);

    Selling createSelling(Selling selling, Product product, String size, int highPrice, int lowPrice);
}
