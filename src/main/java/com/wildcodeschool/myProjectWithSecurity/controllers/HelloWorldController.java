package com.wildcodeschool.myProjectWithSecurity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/")
    public String hello() {
        return "Welcome to the SHIELD";
    }
    @GetMapping("/avengers/assemble")
    public String admin() {
        return "Avengers... Assemble";
    }

    @GetMapping("/secret-bases")
    public String hasbu() {
        return "Welcome Hasbu, our Hero !";
    }
}
