package com.example.jpatest.guestbook;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity //테이블 클래스 정의. 지정된 vo 클래스와 동일한 이름의 테이블이
        //자동으로 생성되고, 이 클래스의 멤버변수와 동일한 이름과 타입의 컬럼이 생성
public class Guestbook {

    @Id //Primary Key 정의
    @GeneratedValue(strategy = GenerationType.IDENTITY) //중복 안되는 값을 자동 생성(자동 넘버링)
    private int num;

    private String writer;
    private Date pdate;
    private String password;
    private String content;

    @PrePersist //insert 전 자동 호출
    public void postDate() {
        pdate = new Date();
    }
}
