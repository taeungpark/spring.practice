package com.example.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@AllArgsConstructor
public class LoginInfo {
    private int UserId;
    private String email;
    private String name;
    private List<String> roles = new ArrayList<>();

    public LoginInfo(int userId, String email, String name) {
        UserId = userId;
        this.email = email;
        this.name = name;
    }

    public void addRole(String roleName){
        roles.add(roleName);
    }
}
