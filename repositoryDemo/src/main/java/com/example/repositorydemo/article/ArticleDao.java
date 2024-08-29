package com.example.repositorydemo.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleDao extends JpaRepository<Article, Integer> {

    List<Article> findByTitleLike(String title);

    List<Article> findByDataLike(String data);

    @Transactional
    @Modifying
    @Query(value = "UPDATE article SET download_count = download_count + 1 WHERE num = :num", nativeQuery = true)
    void updateDownloadCount(@Param("num") int num);
}
