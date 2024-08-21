package com.example.jdbctemplate.guestbook;

import lombok.*;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GuestBook {

    private int num;
    private String writer;
    private String password;
    private Date wdate;
    private String content;
}
