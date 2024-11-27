package com.example.trady.dto;

import com.example.trady.entity.Product;
import com.example.trady.entity.ProductOption;
import com.example.trady.entity.Selling;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Slf4j
public class SellingForm {

    Long id;
    Product sproduct;  // 상품
    String size;
    String sprice;

    public SellingForm(Long id, Product sproduct, String size, String sprice) {
        this.id = id;
        this.sproduct = sproduct;
        this.size = size;
        this.sprice = sprice;
    }
    public Selling toEntity(){
        return new Selling(sproduct,size,sprice);
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

    public String getSprice() {
        return sprice;
    }

    public void setSprice(String sprice) {
        this.sprice = sprice;
    }
}
