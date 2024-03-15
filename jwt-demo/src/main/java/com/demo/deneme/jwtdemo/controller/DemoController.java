package com.demo.deneme.jwtdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class DemoController {

    @GetMapping(value = "/")
    public String home(Principal principal) {
        return "Welcome to Jwt Demo App, " + principal.getName();
    }
}
