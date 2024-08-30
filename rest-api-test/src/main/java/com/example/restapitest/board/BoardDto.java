package com.example.restapitest.board;

import com.example.restapitest.member.Member;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDto {

    private int num;
    private Member writer;
    private Date wdate;
    private String title;
    private String content;
}
