package com.demo.deneme.jwtdemo.controller;

import com.demo.deneme.jwtdemo.config.JwtSecurityConfig;
import com.demo.deneme.jwtdemo.service.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest({DemoController.class, AuthController.class})
@Import({JwtSecurityConfig.class, TokenService.class})
public class DemoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void rootWhenUnauthenticatedThen401() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void rootWhenAuthenticatedThenSaysHelloUser() throws Exception {
        MvcResult result = this.mvc.perform(MockMvcRequestBuilders.post("/token")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "pass")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String token = result.getResponse().getContentAsString();

        this.mvc.perform(MockMvcRequestBuilders.get("/")
                        .header("Authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.content().string("Welcome to Jwt Demo App, admin"));
    }

    @Test
    @WithMockUser
    public void rootWithMockUserStatusIsOK() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}