package com.example.task1.guestbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GuestBookService {

    @Autowired
    private GuestBookDao gbDao;

    public GuestBook getGuestBook(int num) {
        return gbDao.select(num);
    }

    public void addGuestBook(GuestBook gb){
        gbDao.insert(gb);
    }

    public ArrayList<GuestBook> getAll(){
        return gbDao.selectAll();
    }

    public void editGuestBook(GuestBook gb){
        gbDao.update(gb);
    }
}
