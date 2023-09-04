package com.example.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Component to respond HTTP request. Spring boot will create as a bean automatically
@Controller
public class BoardController {
    @GetMapping("/")
    public String list(){
        return "list";
    }

    @GetMapping("/board")
    public String board(@RequestParam("id") int id){
        return "board";
    }

    @GetMapping("/writeForm")
    public String writeForm(){

        return "writeForm";
    }

    @PostMapping("/write")
    public String write(
            @RequestParam("title") String title,
            @RequestParam("content") String content
    ) {
        System.out.println("title : " + title);
        System.out.println("content : " + content);
        return "redirect:/";
    }
}
