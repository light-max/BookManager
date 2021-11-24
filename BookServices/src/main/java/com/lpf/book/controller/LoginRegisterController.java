package com.lpf.book.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRegisterController {
    @GetMapping("/login")
    public String login(){
        return "lgoin";
    }
}
