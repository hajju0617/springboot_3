package org.example.springboot_3.controller;

import org.example.springboot_3.dto.ArticleForm;
import org.example.springboot_3.entity.Article;
import org.example.springboot_3.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        System.out.println(form.toString());

        // 1. DTO를 entity로 변환
        Article article = form.toEntity();
        System.out.println(article.toString());

        // 2. 리파지토리로 Entity를 DB에 저장.
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());
        return "";
    }
}
