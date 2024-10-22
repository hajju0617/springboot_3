package org.example.springboot_3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor // 클래스 안쪽의 모든 필드를 매개변수로 받는 생성자를 자동으로 생성
@ToString(exclude = "comments")     // comments 필드 제외 (안 할 경우 toString 순환 참조로 인해 StackOverflowError 발생)
@NoArgsConstructor  // 기본 생성자 추가
/*
1. JPA 및 Hibernate에서의 Entity 생성
JPA와 같은 ORM(Object-Relational Mapping) 프레임워크는 엔티티 클래스의 인스턴스를 생성할 때 기본 생성자를 사용함.
Hibernate는 리플렉션(Reflection)을 사용하여 엔티티 객체를 생성하는데 이때 기본 생성자가 필요함.
엔티티 클래스를 데이터베이스에서 조회하거나 새로운 데이터를 삽입할 때 Hibernate는 기본 생성자를 사용하여 객체를 인스턴스화 한다.
만약 기본 생성자가 없으면, Hibernate는 객체를 생성할 수 없어서 InstantiationException 같은 예외가 발생.
 */
@Entity // 해당 클래스가 Entity임을 선언. (@Entity는 JPA에서 제공하는 에노테이션)
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    // article에 댓글있어도 article 삭제 가능
    // 하나의 article에 여러개의 comment
    /*
    mappedBy = "article" : 양방향 관계에서 사용하는 속성임.
    Comment 엔터티의 article 필드가 관계를 정의하는 주체임을 의미함.
    mappedBy는 어느 쪽이 외래 키를 관리하는지를 결정하고 Comment 엔터티가 외래 키(article_id)를 관리하고 있으므로, Article 쪽은 mappedBy로 이를 지정하는 것.
    이로 인해 Article 엔터티에서는 Comment의 목록을 가지고 있지만, 실제 DB 상에서는 Comment 엔터티가 외래 키를 통해 연결되어 있음.

    cascade = CascadeType.REMOVE:
    **연쇄 삭제(Cascade Delete)**를 설정하는 옵션.
    글(Article)을 삭제할 때, 그 글에 속한 모든 댓글(Comment)도 함께 삭제되도록 설정.

    orphanRemoval = true:
    고아 객체 제거 기능. Article에서 Comment가 더 이상 참조되지 않으면, 해당 Comment를 자동으로 삭제합니다.
    즉, Article에서 댓글 목록에서 특정 댓글을 제거하거나 전체를 제거하면, 더 이상 참조되지 않는 댓글은 자동으로 DB에서 삭제됩니다.
     */
    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Article(Long id, String title, String content) {     // 생성자 추가
    }


    public void patch(Article article) {
        if (article.title != null) {
            this.title = article.title;
        }
        if (article.content != null) {
            this.content = article.content;
        }
    }


//    public Article(Long id, String title, String content) {
//        this.Id = id;
//        this.title = title;
//        this.content = content;
//    }
//
//    public Article() {
//
//    }
//
//    @Override
//    public String toString() {
//        return "Article{" +
//                "Id=" + Id +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }
}
