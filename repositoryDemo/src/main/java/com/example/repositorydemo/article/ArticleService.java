package com.example.repositorydemo.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    public ArticleDto saveArticle(ArticleDto articleDto) {
        Article entity = articleDao.save(new Article(articleDto.getNum(), articleDto.getWriter(),
                articleDto.getTitle(), articleDto.getContent(),
                articleDto.getWdate(), articleDto.getData(), articleDto.getDownloadCount()));
        return new ArticleDto(entity.getNum(), entity.getWriter(), entity.getTitle(),
                entity.getContent(), entity.getWdate(), entity.getData(),
                entity.getDownloadCount(), null);
    }

    public ArticleDto getArticle(int num) {
        Article article = articleDao.findById(num).orElse(null);
        if (article != null) {
            return new ArticleDto(article.getNum(), article.getWriter(), article.getTitle(),
                    article.getContent(), article.getWdate(), article.getData(),
                    article.getDownloadCount(), null);
        }
        return null;
    }

    public List<ArticleDto> getAllArticles() {
        List<Article> tmp = articleDao.findAll();
        List<ArticleDto> list = new ArrayList<>();
        for (Article entity : tmp) {
            list.add(new ArticleDto(entity.getNum(), entity.getWriter(), entity.getTitle(),
                    entity.getContent(), entity.getWdate(), entity.getData(),
                    entity.getDownloadCount(), null));
        }
        return list;
    }

    public List<ArticleDto> getArticlesByTitle(String title) {
        List<Article> tmp = articleDao.findByTitleLike("%" + title + "%");
        List<ArticleDto> list = new ArrayList<>();
        for (Article entity : tmp) {
            list.add(new ArticleDto(entity.getNum(), entity.getWriter(), entity.getTitle(),
                    entity.getContent(), entity.getWdate(), entity.getData(),
                    entity.getDownloadCount(), null));
        }
        return list;
    }

    public List<ArticleDto> getByData(String data) {
        List<Article> tmp = articleDao.findByDataLike("%" + data);
        List<ArticleDto> list = new ArrayList<>();
        for (Article entity : tmp) {
            list.add(new ArticleDto(entity.getNum(), entity.getWriter(), entity.getTitle(),
                    entity.getContent(), entity.getWdate(), entity.getData(),
                    entity.getDownloadCount(), null));
        }
        return list;
    }

    public void deleteArticle(int num) {
        articleDao.deleteById(num);
    }

    public void updateDownloadCount(int num) {
        articleDao.updateDownloadCount(num);
    }
}
