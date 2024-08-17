package org.example.springboot_3.repository;

import org.example.springboot_3.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {

    @Override
    ArrayList<Article> findAll();
    /*
    특정 메서드가 반환하는 데이터 타입과 사용자가 작성한 반환 데이터 타입이  다를 경우 : 3가지 방법으로 해결
    Iterable, Collection, List 인터페이스의 상하관계 : Iterable <- Collection <- List <- (ArrayList 얘는 class임)
    1. 업 캐스팅
    2. 다운 캐스팅
    3. 원하는 타입으로 오버라이딩
     */
}
