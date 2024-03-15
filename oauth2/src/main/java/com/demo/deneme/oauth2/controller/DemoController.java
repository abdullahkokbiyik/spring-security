package com.demo.deneme.oauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/")
    public String defaultHome() {
        return "Home Page";
    }

    @GetMapping("/secured")
    public String securedHome() {
        return "Secured Home Page";
    }
}
