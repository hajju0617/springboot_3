package org.example.springboot_3.api;

import lombok.extern.slf4j.Slf4j;
import org.example.springboot_3.dto.CoffeeForm;
import org.example.springboot_3.entity.Coffee;
import org.example.springboot_3.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CoffeeApiController {
    @Autowired
    private CoffeeRepository coffeeRepository;

    @PostMapping("/api/coffee")
    public Coffee create(@RequestBody CoffeeForm dto) {
        log.info("dto : {}", dto);
        Coffee coffee = dto.toEntity();
        log.info("coffee : {}", coffee);
        return coffeeRepository.save(coffee);
    }

    @GetMapping("/api/coffee")
    public List<Coffee> index() {
        return coffeeRepository.findAll();
    }

    @GetMapping("/api/coffee/{id}")
    public ResponseEntity<Coffee> show(@PathVariable Long id) {
        Coffee coffee = coffeeRepository.findById(id).orElse(null);
        if (coffee == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(coffee);
    }

    @PatchMapping("/api/coffee/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeForm dto) {
        Coffee coffee = dto.toEntity();
        Coffee result = coffeeRepository.findById(id).orElse(null);
        if (result == null || id != coffee.getId()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        result.patch(coffee);
        Coffee updated = coffeeRepository.save(result);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/api/coffee/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id) {
        Coffee coffee = coffeeRepository.findById(id).orElse(null);
        if (coffee == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        coffeeRepository.delete(coffee);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
