package com.example.jpatest;

import com.example.jpatest.guestbook.GuestbookDto;
import com.example.jpatest.guestbook.GuestbookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private GuestbookService service;

    @Test
    void addTest() {
        service.saveBook(new GuestbookDto(0, "bbb", null, "bbb", "글내용"));
    }

    @Test
    void listTest() {
        ArrayList<GuestbookDto> list = service.getAll();
        System.out.println(list);
    }

    @Test
    void deleteTest() {
        service.delBook(1);
    }
}
