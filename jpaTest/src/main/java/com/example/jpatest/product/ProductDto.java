package com.example.jpatest.product;

import com.example.jpatest.member.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {

    private int num;
    private String name;
    private int price;
    private int amount;
    private Member id;
    private String img;
    private MultipartFile multipartFile; //폼에서 전송한 이미지를 저장할 변수
    //private MultipartFile[] multipartFile => 여러 사진을 저장하고 싶을 경우 배열을 사용
}
