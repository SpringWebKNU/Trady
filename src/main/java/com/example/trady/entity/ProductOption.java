package com.example.trady.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@ToString
@Slf4j
@NoArgsConstructor
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // ManyToOne 관계에서 Product 엔티티의 id를 참조하는 설정
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")  // 'product_id'는 'Product'의 'id'를 참조
            Product product;

    String size;
    int highPrice;
    int lowPrice;

    public ProductOption(Long id, Product product, String size, int highPrice, int lowPrice) {
        this.id = id;
        this.product = product;
        this.size = size;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(int highPrice) {
        this.highPrice = highPrice;
    }

    public int getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(int lowPrice) {
        this.lowPrice = lowPrice;
    }
}
