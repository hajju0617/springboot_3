package org.example.springboot_3.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstApiController {

    @GetMapping("/api/hello")
    public String hello() {
        return "hello world";
    }
}
