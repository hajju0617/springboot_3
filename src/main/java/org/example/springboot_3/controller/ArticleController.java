package org.example.springboot_3.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.springboot_3.dto.ArticleForm;
import org.example.springboot_3.entity.Article;
import org.example.springboot_3.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        log.info(form.toString());

        // 1. DTO를 entity로 변환
        Article article = form.toEntity();
        log.info(article.toString());

        // 2. 리파지토리로 Entity를 DB에 저장.
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        return "";
    }

    @GetMapping("/articles/{id}")   // 중괄호 안에 id를 써주면 id는 변수로 사용
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);

        // 1. id 조회 -> 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 2. 모델에 데이터 등록
        model.addAttribute("article", articleEntity);
        // 3. 뷰 페이지 반환
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        // 1. 모든 데이터 가져오기
        List<Article> articleEntityList = articleRepository.findAll();  // findAll() 반환타입이 Iterable인데 List라서 불일치 -> repository에서 오버라이딩으로 해결
        // 2. 모델에 데이터 등록
        model.addAttribute("articleList", articleEntityList);
        // 3. 뷰 페이지 설정
        return "articles/index";
    }
}
