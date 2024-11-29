package com.example.trady.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

@Entity @ToString @Slf4j @NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String pname;
    private long pprice;
    private String formattedPrice;  // 포맷된 가격을 저장할 필드

    private String pimg;

    // Category와 연관 관계 설정
    @ManyToOne
    @JoinColumn(name = "pcategory_id",  referencedColumnName = "id") // 외래 키 이름
    private Pcategory pcategory;


    @Column(nullable = false)
    private LocalDateTime pdate; // 변경된 필드 타입


    public String getPcategoryName() {
        return pcategory != null ? pcategory.getPname() : "카테고리 없음";
    }

    public boolean isInCategory(Long categoryId) {
        return this.pcategory != null && this.pcategory.getId().equals(categoryId);
    }

    public Product(Long id, String pname, long pprice, String formattedPrice, String pimg, Pcategory pcategory, LocalDateTime pdate) {
        this.id = id;
        this.pname = pname;
        this.pprice = pprice;
        this.formattedPrice = formattedPrice;
        this.pimg = pimg;
        this.pcategory = pcategory;
        this.pdate = pdate;
    }

    public Long getId() {
        return id;
    }

    public String getPname() {
        return pname;
    }


    public String getPimg() {
        return pimg;
    }

    public Pcategory getPcategory() {
        return pcategory;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }


    public void setPimg(String pimg) {
        this.pimg = pimg;
    }


    public void setPcategory(Pcategory pcategory) {
        this.pcategory = pcategory;
    }

    public LocalDateTime getPdate() {
        return pdate;
    }

    public void setPdate(LocalDateTime pdate) {
        this.pdate = pdate;
    }

    public long getPprice() {
        return pprice;
    }

    public void setPprice(long pprice) {
        this.pprice = pprice;
    }


    public String getFormattedPrice() {
        if (formattedPrice == null) {
            DecimalFormat formatter = new DecimalFormat("#,###");
            formattedPrice = formatter.format(this.pprice);
        }
        return formattedPrice;
    }

    public void setFormattedPrice(String formattedPrice) {
        this.formattedPrice = formattedPrice;
    }

    public void logInfo(){
        log.info("id: {}, pname: {}, pprice: {}, pimg: {}", id, pname, pprice, pimg);
    }



}
