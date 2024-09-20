package org.example.springboot_3.api;

import lombok.extern.slf4j.Slf4j;
import org.example.springboot_3.dto.CoffeeForm;
import org.example.springboot_3.entity.Coffee;
import org.example.springboot_3.repository.CoffeeRepository;
import org.example.springboot_3.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CoffeeApiController {
    @Autowired
    private CoffeeService coffeeService;

    @PostMapping("/api/coffee")
    public ResponseEntity<Coffee> create(@RequestBody CoffeeForm dto) {
        Coffee created = coffeeService.create(dto);
        return (created != null) ? ResponseEntity.status(HttpStatus.CREATED).body(created) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/api/coffee")
    public List<Coffee> index() {
        return coffeeService.index();
    }

    @GetMapping("/api/coffee/{id}")
    public ResponseEntity<Coffee> show(@PathVariable Long id) {
        Coffee coffee = coffeeService.show(id);

        return (coffee != null) ? ResponseEntity.status(HttpStatus.OK).body(coffee) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/api/coffee/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeForm dto) {
        Coffee updated = coffeeService.update(dto, id);
        return (updated != null) ? ResponseEntity.status(HttpStatus.OK).body(updated) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/api/coffee/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id) {
        Coffee coffee = coffeeService.delete(id);
        return (coffee != null) ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(coffee) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
