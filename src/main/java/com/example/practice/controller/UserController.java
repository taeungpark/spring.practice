package com.example.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @GetMapping("/userRegForm")
    public String userRegForm(){
        return "userRegForm";
    }

    @PostMapping("/userReg")
    public String userReg(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ){
        return "redirect:/welcome";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/loginform")
    public String loginform(){
        return "loginform";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ){
        return "redirect:/";
    }

    @GetMapping("logout")
    public String logout(){
        return "redirect:/";
    }
}
