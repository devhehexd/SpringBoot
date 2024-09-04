package com.example.securitytokken.imgboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImgBoardService {

    @Autowired
    private ImgBoardDao imgBoardDao;

    public void save(ImgBoardDto imgBoardDto) {
        imgBoardDao.save(new ImgBoard(imgBoardDto.getNum(), imgBoardDto.getWriter(),
                imgBoardDto.getTitle(), imgBoardDto.getImg()));
    }

    public List<ImgBoardDto> getAll() {
        List<ImgBoard> tmp = imgBoardDao.findAll();
        List<ImgBoardDto> list = new ArrayList<>();
        for (ImgBoard entity : tmp) {
            list.add(new ImgBoardDto(entity.getNum(), entity.getWriter(),
                    entity.getTitle(), entity.getImg(), null));
        }
        return list;
    }
}
