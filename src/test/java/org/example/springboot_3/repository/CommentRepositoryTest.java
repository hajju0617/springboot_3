package org.example.springboot_3.repository;

import org.example.springboot_3.entity.Article;
import org.example.springboot_3.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        // 1. 4번 게시글의 모든 댓글 조회
        {
            // 1. 입력 데이터
            Long articleId = 6L;

            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 3. 예상 데이터
            Article article = new Article(6L, "당신의 인생 영화는?", "댓글 고");
            Comment a = new Comment(1L, article, "Park", "굿 윌 헌팅");
            Comment b = new Comment(2L, article, "Kim", "아이 엠 샘");
            Comment c = new Comment(3L, article, "Choi", "쇼생크 탈출");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력");
        }
        // 2. 1번 게시글의 모든 댓글 조회
        {
            // 1. 입력 데이터
            Long articleId = 1L;

            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 3. 예상 데이터
            Article article = new Article(1L, "가가가가", "1111");
            List<Comment> expected = Arrays.asList();

            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없음");
        }

        // 3. 11번 게시글의 모든 댓글 조회
        {
            Long articleId = 11L;
            // 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상 데이터
            Article article = null;
            List<Comment> expected = Arrays.asList();

            // 비교 및 검증
            assertEquals(expected, comments, "11번 게시글 모든 댓글 조회 (11번 게시글이 없음)");
        }

        // 4. 999번 게시글의 모든 댓글 조회
        {
            Long articleId = 999L;

            // 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상 데이터.
            Article article = null;
            List<Comment> expected = Arrays.asList();

            // 비교 및 검증
            assertEquals(expected, comments, "999번 글 자체가 없음");
        }
        // -1번 게시글 모든 댓글 조회
        {
            Long articleId = -1L;

            // 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상 데이터
            Article article = null;
            List<Comment> expected = Arrays.asList();

            // 비교 및 검증
            assertEquals(expected, comments, "-1번 게시글이 존재하지 않음");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        // 1. 'Park'의 모든 댓글 조회
        {
            // 1. 입력 데이터
            String nickname = "Park";

            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 3. 예상 데이터
            Comment a = new Comment(1L, new Article(6L, "당신의 인생 영화는?", "댓글 고"), nickname, "굿 윌 헌팅");
            Comment b = new Comment(4L, new Article(7L, "당신의 소울 푸드는?", "댓글 고고"), nickname, "치킨");
            Comment c = new Comment(7L, new Article(8L, "당신의 취미는?", "댓글 고고고"), nickname, "조깅");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "Park의 모든 댓글을 출력");
        }

        // "Kim"의 모든 댓글 조회
        {
            String nickname = "Kim";

            // 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 예상 데이터
            Comment a = new Comment(2L, new Article(6L, "당신의 인생 영화는?", "댓글 고"), nickname, "아이 엠 샘");
            Comment b = new Comment(5L, new Article(7L, "당신의 소울 푸드는?", "댓글 고고"), nickname, "샤브샤브");
            Comment c = new Comment(8L, new Article(8L, "당신의 취미는?", "댓글 고고고"), nickname, "유튜브 시청");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "Kim이 작성한 모든 댓글");
        }


        // "null"의 모든 댓글 조회
        {
            String nickname = "null";

            // 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 예상 데이터
            List<Comment> expected = Arrays.asList();

            // 비교 및 검증
            assertEquals(expected, comments, "null의 모든 댓글 조회");
        }

        // ""의 모든 댓글 조회
        {
            String nickname = "";

            // 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 예상 데이터
            List<Comment> expected = Arrays.asList();

            // 비교 및 검증
            assertEquals(expected, comments, "\"\"의 모든 댓글 조회");
        }
    }
}