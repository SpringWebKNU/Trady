package com.example.trady.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
@Entity
@ToString
@Slf4j
@NoArgsConstructor
public class Selling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    // 판매자와의 관계 추가
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userid")
    Member user;  // 판매자 (회원)

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product sproduct;  // 판매 상품

    private String size;
    private int sprice;

    // 생성자 수정 (user 추가)
    public Selling(Member user, Product sproduct, String size, int sprice) {
        this.user = user;
        this.sproduct = sproduct;
        this.size = size;
        this.sprice = sprice;
    }

    // Getters and setters
    public Member getUser() {
        return user;
    }

    public void setUser(Member user) {
        this.user = user;
    }

    public Product getSproduct() {
        return sproduct;
    }

    public void setSproduct(Product sproduct) {
        this.sproduct = sproduct;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getSprice() {
        return sprice;
    }

    public void setSprice(int sprice) {
        this.sprice = sprice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
