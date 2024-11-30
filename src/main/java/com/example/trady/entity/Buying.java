package com.example.trady.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@ToString
@Slf4j
@NoArgsConstructor
public class Buying {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // 회원과의 관계 추가
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userid")
    Member user;  // 구매자 (회원)

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE) // Product 삭제 시 Buying도 삭제
    private Product product;  // 상품

    @ManyToOne
    @JoinColumn(name = "product_option_id", referencedColumnName = "id")
    //@OnDelete(action = OnDeleteAction.SET_NULL)
    ProductOption productOption;  // 선택된 옵션

    // 생성자 수정 (user 추가)
    public Buying(Member user, Product product, ProductOption productOption) {
        this.user = user;
        this.product = product;
        this.productOption = productOption;
    }

    // Getters and setters
    public Member getUser() {
        return user;
    }

    public void setUser(Member user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductOption getProductOption() {
        return productOption;
    }

    public void setProductOption(ProductOption productOption) {
        this.productOption = productOption;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
