package org.example.springboot_3.repository;

import org.example.springboot_3.entity.Coffee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
    @Override
    List<Coffee> findAll();
}
