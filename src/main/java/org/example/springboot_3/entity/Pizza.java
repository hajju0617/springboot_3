package org.example.springboot_3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private double price;
}

/*
HTTP 메서드        요청 주소                   비고
POST            /api/pizzas                 피자 등록(Json 데이터 입력)
GET             /api/pizzas/{id}            피자 단건 조회
GET             /api/pizzas                 피자 목록 조회
PATCH           /api/pizzas/{id}            피자 수정(Json 입력)
DELETE          /api/pizzas/{id}            피자 삭제
 */
