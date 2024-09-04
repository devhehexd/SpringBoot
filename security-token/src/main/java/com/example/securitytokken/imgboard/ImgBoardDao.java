package com.example.securitytokken.imgboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgBoardDao extends JpaRepository<ImgBoard, Integer> {
}
