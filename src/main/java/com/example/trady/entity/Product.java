package com.example.trady.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity @ToString @Slf4j @NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String pname;
    private String pprice;
    private String pimg;


    // Constructor
    public Product(Long id, String pname, String pprice, String pimg) {
        this.id = id;
        this.pname = pname;
        this.pprice = pprice;
        this.pimg = pimg;
    }

//    public void patch(Product product) {
//        if (product.pname != null) {
//            this.pname = product.pname;
//        }
//        if (product.pprice != null) {
//            this.pprice = product.pprice;
//        }
//    }


    public Long getId() {
        return id;
    }

    public String getPname() {
        return pname;
    }

    public String getPprice() {
        return pprice;
    }

    public String getPimg() {
        return pimg;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setPprice(String pprice) {
        this.pprice = pprice;
    }

    public void setPimg(String pimg) {
        this.pimg = pimg;
    }

    public void logInfo(){
        log.info("id: {}, pname: {}, pprice: {}, pimg: {}", id, pname, pprice, pimg);
    }

}
