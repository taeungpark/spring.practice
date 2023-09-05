package com.example.practice.controller;

import com.example.practice.dto.LoginInfo;
import com.example.practice.dto.User;
import com.example.practice.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

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
        System.out.println("name = " + name);
        System.out.println("email = " + email);
        System.out.println("password = " + password);

        userService.addUser(name, email, password);
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
            @RequestParam("password") String password,
            HttpSession httpSession
    ){
        try{
            User user = userService.getUser(email);
            if(user.getPassword().equals(password)){
                System.out.println("same password");
                LoginInfo loginInfo = new LoginInfo(user.getUserId(), user.getEmail(), user.getName());

                List<String> roles = userService.getRoles(user.getUserId());
                loginInfo.setRoles(roles);

                httpSession.setAttribute("loginInfo", loginInfo);
                System.out.println("login info is save at session");
            } else {
                throw new RuntimeException("wrong password");
            }
        }catch (Exception e) {
            return "redirect:/loginform?error=true";
        }
        return "redirect:/";
    }

    @GetMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginInfo");
        return "redirect:/";
    }
}
