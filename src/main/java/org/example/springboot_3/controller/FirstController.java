package org.example.springboot_3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {  // model 객체 받아오기
        model.addAttribute("username", "하주원");  // model 객체가 "하주원"값을 "username"에 연결해 웹 브라우저로 보냄.
        return "greetings";     // greetings.mustache 파일 반환
    }
}
