package org.example.springboot_3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.springboot_3.entity.Comment;

@AllArgsConstructor         // 모든 필드를 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor          // 매개변수가 아예 없는 기본 생성자 자동 생성
@ToString                   // 모든 필드를 출력할 수 있는 toString 메서드 자동 생성
@Getter                     // 각 필드 값을 조회할 수 있는 getter 메서드 자동 생성
public class CommentDto {
    private Long id;
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentDto crateCommentDto(Comment comment) {
        return new CommentDto(
                  comment.getId()               // 댓글 엔티티의 id
                , comment.getArticle().getId()  // 댓글 엔티티가 속한 게시글의 id
                , comment.getNickname()         // 댓글 엔티티의 nickname
                , comment.getBody()             // 댓글 엔티티의 body
        );
    }
}
