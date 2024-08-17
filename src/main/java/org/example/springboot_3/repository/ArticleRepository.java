package org.example.springboot_3.repository;

import org.example.springboot_3.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
