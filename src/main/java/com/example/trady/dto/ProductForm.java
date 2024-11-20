package com.example.trady.dto;

import com.example.trady.entity.Product;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString @Slf4j
public class ProductForm {

    private Long id;
    private String pname;
    private String pprice;
    private String pimg;

    public Product toEntity(){
        return new Product(id,pname,pprice,pimg);
    }

    public void logInfo(){
        log.info("id: {}, pname: {}, pprice: {}, pimg: {}", id, pname, pprice, pimg);
    }

}
