package com.example.restapitest.member;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Member {

    @Id
    private String id;

    private String password;
    private String name;
    private String email;
    private String type;
}
