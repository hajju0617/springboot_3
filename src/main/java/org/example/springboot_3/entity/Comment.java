package org.example.springboot_3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity             // 해당 클래스가 엔티티임을 선언, 클래스 필드를 바탕으로 DB에 테이블 생성.
@Getter             // 각 필드 값을 조회할 수 있는 getter 메서드 자동 생성
@ToString           // 모든 필드를 출력할 수 있는 toString 메서드 자동 생성
@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor  // 매개변수가 아예 없는 기본 생성자 자동 생성
public class Comment {
    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne  // 다대일
    @JoinColumn(name = "article_id")    // FK, Article 엔티티의 PK와 매핑.
    private Article article;

    @Column     // 해당 필드를 테이블의 속성으로 매핑.
    private String nickname;

    @Column
    private String body;
}
