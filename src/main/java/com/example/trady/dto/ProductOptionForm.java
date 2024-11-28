package com.example.trady.dto;

import com.example.trady.entity.Product;
import com.example.trady.entity.ProductOption;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Slf4j
public class ProductOptionForm {

    private Long id;
    private Product product;
    private String size;
    private int price;

    public ProductOption toEntity() {
        return new ProductOption(id, product, size, price);
    }


    public void logInfo() {
        log.info("id: {}, product: {}, size: {}, price: {}", id, product, size, price);
    }
}