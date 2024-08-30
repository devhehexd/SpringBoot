package com.example.restapitest.board;

import com.example.restapitest.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardDao boardDao;

    public void saveBoard(BoardDto boardDto) {
        boardDao.save(new Board(boardDto.getNum(), boardDto.getWriter(), boardDto.getWdate(),
                boardDto.getTitle(), boardDto.getContent()));
    }

    public BoardDto getBoard(int num) {
        Board board = boardDao.findById(num).orElse(null);
        if (board != null) {
            return new BoardDto(board.getNum(), board.getWriter(), board.getWdate(),
                    board.getTitle(), board.getContent());
        }
        return null;
    }

    public List<BoardDto> getAllBoards() {
        List<Board> tmp = boardDao.findAll();
        List<BoardDto> list = new ArrayList<>();
        for (Board board : tmp) {
            list.add(new BoardDto(board.getNum(), board.getWriter(), board.getWdate(),
                    board.getTitle(), board.getContent()));
        }
        return list;
    }

    public List<BoardDto> getByTitle(String title) {
        List<Board> tmp = boardDao.findByTitleLike("%" + title + "%");
        List<BoardDto> list = new ArrayList<>();
        for (Board board : tmp) {
            list.add(new BoardDto(board.getNum(), board.getWriter(), board.getWdate(),
                    board.getTitle(), board.getContent()));
        }
        return list;
    }

    public List<BoardDto> getByWriter(String writer) {
        List<Board> tmp = boardDao.findByWriter(new Member(writer, "", "", "", ""));
        List<BoardDto> list = new ArrayList<>();
        for (Board board : tmp) {
            list.add(new BoardDto(board.getNum(), board.getWriter(), board.getWdate(),
                    board.getTitle(), board.getContent()));
        }
        return list;
    }

    public void deleteBoard(int num) {
        boardDao.deleteById(num);
    }
}
