package org.example.springboot_3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor // 클래스 안쪽의 모든 필드를 매개변수로 받는 생성자를 자동으로 생성
@ToString
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
