package com.springdemo.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String homePage() {
        return "homepage";
    }

    @GetMapping("/showLoginPage")
    public String loginShow() {
        return "fancy-login";
    }

    @GetMapping("/managers")
    public String managerShow() {
        return "managerPage";
    }

    @GetMapping("/admins")
    public String adminShow() {
        return "adminPage";
    }

    @GetMapping("/noaccesspage")
    public String denyShow() {
        return "accessDeniedPage";
    }



}