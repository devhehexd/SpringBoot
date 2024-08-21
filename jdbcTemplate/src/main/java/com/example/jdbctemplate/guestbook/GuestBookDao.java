package com.example.jdbctemplate.guestbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class GuestBookDao {

    @Autowired
    private JdbcTemplate temp; //사용할 jdbctemplate 객체 의존성 주입

    //resultMap 정의
    public class GuestResultMap implements RowMapper<GuestBook> {

        //ResultSet의 한 행을 처리. parameter로 처리해야할 행 번호를 받음
        //각 컬럼을 vo 생성자 parameter에 맵핑
        @Override
        public GuestBook mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new GuestBook(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getDate(4), rs.getString(5));
        }
    }


    public void insert(GuestBook gb) {

        String sql = "INSERT INTO guestbook(writer, password, wdate, content) values(?,?,sysdate(),?)";
        temp.update(sql, new Object[]{gb.getWriter(), gb.getPassword(), gb.getContent()});
        //물음표가 하나면 직접 값을 넣어도 되지만, 여러개면 배열을 사용하는 것이 편하다
    }

    public GuestBook select(int num) {

        String sql = "SELECT * FROM guestbook WHERE num = ?";
        GuestBook gb = null;

        try {
            //queryForObject(): 한줄검색
            gb = temp.queryForObject(sql, new GuestResultMap(), num);
        } catch (Exception e) {
            System.out.println(e);
        }
        return gb;

    }

    public ArrayList<GuestBook> selectAll() {

        String sql = "SELECT * FROM guestbook ORDER BY num";

        return (ArrayList<GuestBook>) temp.query(sql, new GuestResultMap());
    }

    public void update(GuestBook gb) {

        String sql = "UPDATE guestbook SET content = ? WHERE num = ?";
        temp.update(sql, new Object[]{gb.getContent(), gb.getNum()});

    }

    public void delete(int num) {
        String sql = "DELETE FROM guestbook WHERE num = ?";
        temp.update(sql, num);
    }
}
