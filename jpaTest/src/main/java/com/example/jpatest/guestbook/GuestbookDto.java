package com.example.jpatest.guestbook;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GuestbookDto {

    private int num;
    private String writer;
    private Date pdate;
    private String password;
    private String content;
}
