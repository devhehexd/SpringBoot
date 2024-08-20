package com.example.task1.guestbook;

import conn.MysqlConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class GuestBookDao {

    private MysqlConnection dbconn;

    public GuestBookDao() {
        dbconn = MysqlConnection.getInstance();
    }

    public GuestBook select(int num) {

        Connection conn = dbconn.getConnection();
        String sql = "SELECT * FROM guestbook WHERE num=?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, num);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                return new GuestBook (rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getDate(4),  rs.getString(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void insert(GuestBook gb) {

        Connection conn = dbconn.getConnection();
        String sql = "INSERT INTO guestbook(writer, password, content, wdate) values(?, ?, ?, sysdate())";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, gb.getWriter());
            pstmt.setString(2, gb.getPassword());
            pstmt.setString(3, gb.getContent());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int num = rs.getInt(1);
                gb.setNum(num);
                Date wdate = rs.getDate(4);
                gb.setWdate(wdate);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<GuestBook> selectAll() {

        ResultSet rs = null;
        ArrayList<GuestBook> gbList = new ArrayList<>();
        Connection conn = dbconn.getConnection();

        String sql = "SELECT * FROM guestbook";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                gbList.add(new GuestBook(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4),
                        rs.getString(5)));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return gbList;
    }

    public void update(GuestBook gb) {

        Connection conn = dbconn.getConnection();
        String sql = "UPDATE guestbook SET content = ? WHERE num = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, gb.getContent());
            pstmt.setInt(2, gb.getNum());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void delete(int num) {
        Connection conn = dbconn.getConnection();
        String sql = "DELETE FROM guestbook WHERE num = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, num);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
