package com.example.mybatistest.member;

import org.apache.ibatis.annotations.*;

@Mapper
public interface MemberDao {

    @Select("SELECT * FROM member WHERE id = #{id}")
    Member select(@Param("id") String id);

    @Insert("INSERT INTO member values(#{id}, #{password}, #{name}, #{email}, #{type})")
    void insert(Member member);

    @Update("UPDATE member SET name = #{name} , email = #{email}, type = #{type} WHERE id = #{id}")
    void update(Member member);

    @Delete("DELETE FROM member WHERE id = #{id}")
    void delete(@Param("id") String id);
}
