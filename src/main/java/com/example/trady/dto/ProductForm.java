package com.example.trady.dto;

import com.example.trady.entity.Pcategory;
import com.example.trady.entity.Product;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Slf4j
public class ProductForm {

    private Long id;
    private String pname;
    private String pprice;
    private String pimg;
    private Long categoryId; // categoryId is just a reference to a Pcategory
    private String pdate;


    // DTO -> Entity 변환
    public Product toEntity(Pcategory pcategory) {
        return new Product(id, pname, pprice, pimg, pcategory, pdate);  // Pcategory도 함께 전달
    }

    public void logInfo() {
        log.info("id: {}, pname: {}, pprice: {}, pimg: {}, categoryId: {}", id, pname, pprice, pimg, categoryId);
    }
}
