package org.example.springboot_3.entity;

import jakarta.persistence.*;

@Entity // 해당 클래스가 Entity임을 선언. (@Entity는 JPA에서 제공하는 에노테이션)
public class Article {

    @Id
    @GeneratedValue
    private Long Id;

    @Column
    private String title;

    @Column
    private String content;

    public Article(Long id, String title, String content) {
        this.Id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "Id=" + Id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
