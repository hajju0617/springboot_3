package org.example.springboot_3.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.springboot_3.dto.ArticleForm;
import org.example.springboot_3.dto.CommentDto;
import org.example.springboot_3.entity.Article;
import org.example.springboot_3.repository.ArticleRepository;
import org.example.springboot_3.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;      // 서비스 객체 주입

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
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")   // 중괄호 안에 id를 써주면 id는 변수로 사용
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);

        // 1. id 조회 -> 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        List<CommentDto> commentDtos = commentService.comments(id);     // 댓글 목록 가져오기.
        // 2. 모델에 데이터 등록
        model.addAttribute("article", articleEntity);

        model.addAttribute("commentDtos", commentDtos);     // 댓글 목록을 모델에 등록.
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

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);

        return "articles/edit";  // 뷰 페이지 설정
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());

        // 1. DTO -> Entity 변환
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        // 2. 엔티티를 DB에 저장
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        if (target != null) {
            articleRepository.save(articleEntity);
        }

        // 3. 수정 결과 페이지로 redirect
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        log.info("삭제 요청 들어옴");

        // 1. 삭제할 거 가져오기
        Article result = articleRepository.findById(id).orElse(null);
        log.info(result.toString());
        // 2. 엔티티 삭제
        if (result != null) {
            articleRepository.delete(result);
            rttr.addFlashAttribute("msg", "삭제 됐음");
        }
        // 3. 결과 페이지로 리다이렉트

        return "redirect:/articles";
    }
}
