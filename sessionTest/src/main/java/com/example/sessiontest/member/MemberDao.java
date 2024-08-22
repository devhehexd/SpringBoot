package com.example.sessiontest.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class MemberDao {

    @Autowired
    private JdbcTemplate temp;

    public class MemberResultMap implements RowMapper<Member> {

        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Member(rs.getString(1), rs.getString(2),
                    rs.getString(3), rs.getString(4),
                    rs.getString(5));
        }
    }

    public void insert(Member member) {
        String sql = "INSERT INTO member values(?, ?, ?, ?, ?)";
        temp.update(sql, new Object[]{member.getId(), member.getPassword(),
                member.getName(), member.getEmail(), member.getType()});
    }

    public Member select(String id) {
        String sql = "SELECT * FROM member WHERE id = ?";
        Member member = null;

        try {
            member = temp.queryForObject(sql, new MemberResultMap(), id);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return member;
    }

    public void update(Member member) {
        String sql = "UPDATE member SET name = ?, email = ?, type = ? WHERE id = ?";
        temp.update(sql, new Object[]{member.getName(), member.getEmail(),
                member.getType(), member.getId()});
    }

    public void delete(String id) {
        String sql = "DELETE FROM member WHREE id = ?";
        temp.update(sql, id);
    }
}
