package com.example.trady.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@ToString
@Slf4j
@NoArgsConstructor
public class Buying {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "userid")
//    Member user;  // 구매자 (회원)

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    Product product;  // 상품

    @ManyToOne
    @JoinColumn(name = "product_option_id", referencedColumnName = "id")
    ProductOption productOption;  // 선택된 옵션

//    public Buying(Member user, Product product, ProductOption productOption) {
//        this.id = id;
//        this.user = user;
//        this.product = product;
//        this.productOption = productOption;
//    }


    public Buying( Product product, ProductOption productOption) {
        this.id = id;
        this.product = product;
        this.productOption = productOption;
    }
    public ProductOption getProductOption() {
        return productOption;
    }

    public void setProductOption(ProductOption productOption) {
        this.productOption = productOption;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
