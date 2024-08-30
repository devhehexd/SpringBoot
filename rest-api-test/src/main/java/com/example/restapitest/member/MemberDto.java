package com.example.restapitest.member;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDto {

    private String id;
    private String password;
    private String name;
    private String email;
    private String type;
}
