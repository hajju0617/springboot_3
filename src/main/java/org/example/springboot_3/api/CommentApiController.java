package org.example.springboot_3.api;

import org.example.springboot_3.dto.CommentDto;
import org.example.springboot_3.entity.Comment;
import org.example.springboot_3.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    // 1. 댓글 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {

        // 서비스
        List<CommentDto> dtos = commentService.comments(articleId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


    // 2. 댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto) {

        // 서비스에 위임
        CommentDto commentDto = commentService.create(articleId, dto);

        // 결과 반환
        return ResponseEntity.status(HttpStatus.OK).body(commentDto);
    }


    // 3. 댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto) {
        // 서비스에 위임
        CommentDto updateDto = commentService.update(id, dto);

        // 결과 반환
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }


    // 4. 댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id) {
        // 서비스에 위임
        CommentDto deleteDto = commentService.delete(id);
        // 결과 반환
        return ResponseEntity.status(HttpStatus.OK).body(deleteDto);
    }
}
