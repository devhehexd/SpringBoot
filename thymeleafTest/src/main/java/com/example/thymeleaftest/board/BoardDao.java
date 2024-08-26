package com.example.thymeleaftest.board;

import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface BoardDao {

    @Insert("INSERT INTO board(writer, wdate, title, content) values(#{writer}, sysdate(), #{title}, #{content})")
    public void insert(Board board);

    @Select("SELECT * FROM board where num = #{num}")
    public Board select(@Param("num") int num);

    @Select("SELECT *FROM board ORDER BY NUM")
    public ArrayList<Board> selectAll();

    @Select("SELECT * FROM board WHERE title LIKE #{title} ORDER BY num")
    public ArrayList<Board> selectByTitle(@Param("title") String title);

    @Select("SELECT * FROM board WHERE writer = #{writer} ORDER BY num")
    public ArrayList<Board> selectByWriter(@Param("writer") String writer);

    @Update("UPDATE board SET title = #{title}, content={content} WHERE num = #{num}")
    public void update(Board board);

    @Delete("DELETE FROM board where num = #{num}")
    public void delete(@Param("num") int num);
}
