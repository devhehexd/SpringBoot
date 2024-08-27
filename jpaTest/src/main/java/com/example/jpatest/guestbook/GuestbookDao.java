package com.example.jpatest.guestbook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface GuestbookDao extends JpaRepository<Guestbook, Integer> { //Integer는 pk의 타입

    //Service에 getByWriter()구현
    ArrayList<Guestbook> findByWriter(String writer);
}
