package com.example.jdbctemplate.guestbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GuestBookService {

    @Autowired
    private GuestBookDao gbDao;

    //추가
    public void addGuestBook(GuestBook gb) {
        gbDao.insert(gb);
    }

    //번호로 검색
    public GuestBook getGuestBook(int num) {
        return gbDao.select(num);
    }

    //수정
    public void editGuestBook(GuestBook gb) {
        gbDao.update(gb);
    }

    //삭제
    public void deleteGuestBook(int num) {
        gbDao.delete(num);
    }

    //전체목록
    public ArrayList<GuestBook> getAll(){
        return gbDao.selectAll();
    }
}
