package com.example.sessiontest.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class BoardDao {

    @Autowired
    private JdbcTemplate temp;

    public class BoardMapper implements RowMapper<Board> {

        @Override
        public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Board(rs.getInt(1), rs.getString(2),
                    rs.getDate(3), rs.getString(4),
                    rs.getString(5));
        }
    }

    public void insert(Board board) {
        String sql = "INSERT INTO board(writer, wdate, title, content) value(?, sysdate(), ?, ?)";
        temp.update(sql, new Object[]{board.getWriter(), board.getTitle(), board.getContent()});
    }

    public Board select(int num) {
        String sql = "SELECT * FROM board WHERE num = ?";
        Board board = null;

        try {
            board = temp.queryForObject(sql, new BoardMapper(), num);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return board;
    }

    public ArrayList<Board> selectAll() {
        String sql = "SELECT * FROM board";
        ArrayList<Board> list = null;

        try {
            list = (ArrayList<Board>) temp.query(sql, new BoardMapper());
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public ArrayList<Board> selectByTitle(String title) {
        String sql = "SELECT * FROM board WHERE title LIKE CONCAT('%', ?, '%') ORDER BY num";
        ArrayList<Board> list = (ArrayList<Board>) temp.query(sql, new BoardMapper(), title);
        return list;
    }

    public ArrayList<Board> selectByWriter(String writer) {
        String sql = "SELECT * FROM board WHERE writer Like CONCAT('%', ?, '%') ORDER BY num";
        ArrayList<Board> list = (ArrayList<Board>) temp.query(sql, new BoardMapper(), writer);
        return list;
    }

    //제목, 내용 수정
    public void update(Board board) {
        String sql = "UPDATE board SET title = ?, content= ? WHERE num = ?";
        temp.update(sql, new Object[]{board.getTitle(), board.getContent(), board.getNum()});
    }

    public void delete(int num) {
        String sql = "DELETE FROM board WHERE num = ?";
        temp.update(sql, num);
    }
}
