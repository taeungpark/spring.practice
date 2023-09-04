package com.example.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginInfo {
    private int UserId;
    private String email;
    private String name;
}
