package com.example.jpatest.guestbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class GuestbookService {

    @Autowired
    private GuestbookDao gbDao;

    //글 작성 및 수정
    //save(): 방금 추가/수정 된 행을 entity에 담아서 반환
    public GuestbookDto saveBook(GuestbookDto gbDto) {
        Guestbook entity = gbDao.save(new Guestbook(gbDto.getNum(), gbDto.getWriter(), gbDto.getPdate(),
                gbDto.getPassword(), gbDto.getContent()));
        return new GuestbookDto(entity.getNum(), entity.getWriter(), entity.getPdate(),
                entity.getPassword(), entity.getContent());
    }

    //전체 목록
    public ArrayList<GuestbookDto> getAll() {
        ArrayList<Guestbook> tmp = (ArrayList<Guestbook>) gbDao.findAll();
        ArrayList<GuestbookDto> list = new ArrayList<>();

        for (Guestbook entity : tmp) {
            list.add(new GuestbookDto(entity.getNum(), entity.getWriter(), entity.getPdate(),
                    entity.getPassword(), entity.getContent()));
        }
        return list;
    }

    //pk(num)로 검색
    public GuestbookDto getBook(int num) {
        Guestbook entity = gbDao.findById(num).orElse(null);
        if (entity != null) {
            return new GuestbookDto(entity.getNum(), entity.getWriter(), entity.getPdate(),
                    entity.getPassword(), entity.getContent());
        }
        return null;
    }

    //삭제
    public void delBook(int num) {
        gbDao.deleteById(num);
    }

    //작성자로 검색
    public ArrayList<GuestbookDto> getByWriter(String writer) {
        ArrayList<Guestbook> tmp = gbDao.findByWriter(writer);
        ArrayList<GuestbookDto> list = new ArrayList<>();
        for (Guestbook entity : tmp) {
            list.add(new GuestbookDto(entity.getNum(), entity.getWriter(), entity.getPdate(),
                    entity.getPassword(), entity.getContent()));
        }
        return list;
    }
}
