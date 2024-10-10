package org.example.springboot_3.service;

import org.example.springboot_3.dto.CommentDto;
import org.example.springboot_3.entity.Article;
import org.example.springboot_3.entity.Comment;
import org.example.springboot_3.repository.ArticleRepository;
import org.example.springboot_3.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
//        // 1. 댓글 조회
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//
//        // 2. 엔티티 -> Dto 변환
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//        for (int i = 0; i < comments.size(); i++) {
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.crateCommentDto(c);
//            dtos.add(dto);
//        }
//        // 3. 결과 반환
//        return dtos;
        // 위의 코드를 하단의 코드로 리팩토링 (스트림(stream) 사용)
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.crateCommentDto(comment))
                .collect(Collectors.toList());
    }
}
