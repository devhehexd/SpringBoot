package com.example.restapitest.board;

import com.example.restapitest.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardDao extends JpaRepository<Board, Integer> {

    List<Board> findByWriter(Member writer);
    List<Board> findByTitleLike(String title);

}
