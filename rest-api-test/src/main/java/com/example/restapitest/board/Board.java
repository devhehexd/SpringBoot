package com.example.restapitest.board;

import com.example.restapitest.member.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 넘버링. auto_increment
    private int num;

    @ManyToOne //board:member => 다대일
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member writer; //연관되는 entity 타입으로 지정

    private Date wdate;
    private String title;
    private String content;

    @PrePersist
    public void writeDate() {
        wdate = new Date();
    }
}
