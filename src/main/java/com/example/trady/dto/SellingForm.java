package com.example.trady.dto;

import com.example.trady.entity.Product;
import com.example.trady.entity.ProductOption;
import com.example.trady.entity.Selling;
import com.example.trady.entity.Member;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Slf4j
public class SellingForm {

    Long id;
    Member member;  // Added reference to Member
    Product sproduct;  // 상품
    String size;
    int sprice;

    public SellingForm(Long id, Member member, Product sproduct, String size, int sprice) {
        this.id = id;
        this.member = member;  // Pass member here
        this.sproduct = sproduct;
        this.size = size;
        this.sprice = sprice;
    }

    public Selling toEntity() {
        return new Selling(member, sproduct, size, sprice);  // Pass member to entity
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getSprice() {
        return sprice;
    }

    public void setSprice(int sprice) {
        this.sprice = sprice;
    }
}
