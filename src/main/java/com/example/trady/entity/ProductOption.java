package com.example.trady.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Slf4j
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;  // 상품 정보

    private String size;  // 사이즈 정보
    private long price;  // 가격 (사용자가 판매할 때 입력한 가격)

    @Transient  // DB에 저장하지 않겠다는 표시
    private String formattedPrice;

    // Getters and setters
    public String getFormattedPrice() {
        return formattedPrice;
    }

    public void setFormattedPrice(String formattedPrice) {
        this.formattedPrice = formattedPrice;
    }

    // Constructor for easy initialization
    public ProductOption(Product product, String size, long price) {
        this.product = product;
        this.size = size;
        this.price = price;
    }

    // Getter and Setter methods...
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
