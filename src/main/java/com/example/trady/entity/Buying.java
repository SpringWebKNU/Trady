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


    private String size;  // 상품 옵션의 사이즈
    private long price; // 상품 옵션의 가격


    @Transient  // DB에 저장하지 않겠다는 표시
    private String formattedPrice;

    // Getters and setters
    public String getFormattedPrice() {
        return formattedPrice;
    }

    public void setFormattedPrice(String formattedPrice) {
        this.formattedPrice = formattedPrice;
    }

    // 기본 옵션 설정
    public ProductOption getProductOption() {
        if (productOption == null) {
            // DB에서 기본 옵션을 조회하거나 미리 지정된 기본값을 반환
            return getDefaultProductOption();  // 실제 기본 옵션 객체를 반환하도록 수정
        }
        return productOption;
    }

    // 기본 옵션을 반환하는 메소드
    private ProductOption getDefaultProductOption() {
        // 여기서 실제 기본 옵션을 조회하거나 정의할 수 있습니다.
        // 예를 들어, productOptionRepository.findDefaultOption() 메소드를 구현하여 기본 옵션을 반환하도록 할 수 있습니다.
        return new ProductOption(); // 또는 실제 기본 옵션 객체를 반환
    }

    public Buying(Member user, Product product, ProductOption productOption, String size, long price) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.productOption = productOption;
        this.size = size;
        this.price = price;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    public Member getUser() {
        return user;
    }

    public void setUser(Member user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}