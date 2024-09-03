package com.example.spring_security.user;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {

    private String id;
    private String password;
    private String type;
}
