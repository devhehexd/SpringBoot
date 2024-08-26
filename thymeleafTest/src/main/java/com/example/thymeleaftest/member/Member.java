package com.example.thymeleaftest.member;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {

    private String id;
    private String password;
    private String name;
    private String email;
    private String type;
}
