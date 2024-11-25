package com.example.trady.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    // Category와 연관 관계 설정
    @ManyToOne
    @JoinColumn(name = "pcategory_id",  referencedColumnName = "id") // 외래 키 이름
    private Pcategory pcategory;


    public String getPcategoryName() {
        return pcategory != null ? pcategory.getPname() : "카테고리 없음";
    }

    public boolean isInCategory(Long categoryId) {
        return this.pcategory != null && this.pcategory.getId().equals(categoryId);
    }



    // Constructor
    public Product(Long id, String pname, String pprice, String pimg, Pcategory pcategory) {
        this.id = id;
        this.pname = pname;
        this.pprice = pprice;
        this.pimg = pimg;
        this.pcategory = pcategory;
    }

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


    public Pcategory getPcategory() {
        return pcategory;
    }

    public void setPcategory(Pcategory pcategory) {
        this.pcategory = pcategory;
    }

    public void logInfo(){
        log.info("id: {}, pname: {}, pprice: {}, pimg: {}", id, pname, pprice, pimg);
    }

}
