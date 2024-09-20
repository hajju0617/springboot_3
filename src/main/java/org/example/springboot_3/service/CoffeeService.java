package org.example.springboot_3.service;

import lombok.extern.slf4j.Slf4j;
import org.example.springboot_3.dto.CoffeeForm;
import org.example.springboot_3.entity.Coffee;
import org.example.springboot_3.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    public Coffee create(CoffeeForm dto) {
        log.info("dto : {}", dto);
        Coffee coffee = dto.toEntity();
        log.info("coffee : {}", coffee);
        if (coffee.getId() != null) {
            return null;
        }
        return coffeeRepository.save(coffee);
    }

    public List<Coffee> index() {
        return coffeeRepository.findAll();
    }

    public Coffee show(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public Coffee update(CoffeeForm dto, Long id) {
        Coffee coffee = dto.toEntity();
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if (target == null || id != coffee.getId()) {
            return null;
        }
        target.patch(coffee);
        return coffeeRepository.save(coffee);
    }

    public Coffee delete(Long id) {
        Coffee coffee = coffeeRepository.findById(id).orElse(null);
        if (coffee == null) {
            return null;
        }
        coffeeRepository.delete(coffee);
        return coffee;
    }
}
