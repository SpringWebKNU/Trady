package com.example.trady.dto;

import com.example.trady.entity.Pcategory;
import com.example.trady.entity.Product;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Slf4j
public class ProductForm {

    private Long id;
    private String pname;
    private long pprice;
    private MultipartFile pimg;
    private Long categoryId; // categoryId is just a reference to a Pcategory
    private LocalDateTime pdate; // 변경된 필드 타입


    // DTO -> Entity 변환
    public Product toEntity(Pcategory pcategory, String filePath) {
        return new Product(id, pname, pprice,null, filePath, pcategory, pdate); // filePath를 추가
    }


    public void logInfo() {
        log.info("id: {}, pname: {}, pprice: {}, pimg: {}, categoryId: {}", id, pname, pprice, pimg, categoryId);
    }

    // Getter 및 Setter 메서드들
    public MultipartFile getPimg() {
        return pimg;
    }

    public void setPimg(MultipartFile pimg) {
        this.pimg = pimg;
    }

}
