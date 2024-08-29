package com.example.repositorydemo.article;

import com.example.repositorydemo.member.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleDto {

    private int num;
    private Member writer;
    private String title;
    private String content;
    private Date wdate;
    private String data;
    private int downloadCount;
    private MultipartFile multipartFile;
}
