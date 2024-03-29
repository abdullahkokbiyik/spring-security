package com.demo.deneme.jwtdemo.controller;

import com.demo.deneme.jwtdemo.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping(value = "/token")
    public String token(Authentication authentication) {
        logger.info("Token requested for user: '{}'", authentication.getName());
        String token = tokenService.generateToken(authentication);
        logger.info("Token generated, {}", token);
        return token;
    }
}
