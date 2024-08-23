package com.example.sessiontest.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BoardService {

    @Autowired
    private BoardDao bdDao;

    public void addBoard(Board board) {
        bdDao.insert(board);
    }

    public Board getBoard(int id) {
        return bdDao.select(id);
    }

    public ArrayList<Board> getAllBoards() {
        return bdDao.selectAll();
    }

    public ArrayList<Board> getBoardsByTitle(String title) {
        return bdDao.selectByTitle("%" + title + "%");
    }

    public ArrayList<Board> getBoardsByWriter(String writer) {
        return bdDao.selectByWriter(writer);
    }

    public void editBoard(Board board) {
        bdDao.update(board);
    }

    public void deleteBoard(int id) {
        bdDao.delete(id);
    }
}
