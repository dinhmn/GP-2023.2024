package com.graduationproject.backend.backendwebsiteshoe.controller;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/index")
    @NonNull
    public String index() {
        return "<h1>Hello Graduation Project 2022-2023</h1>";
    }
}
