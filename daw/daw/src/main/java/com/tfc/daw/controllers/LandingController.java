package com.tfc.daw.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LandingController {
    @GetMapping("/landing")
    public Resource gestionReservas() {
        return new ClassPathResource("/static/html/landing.html");
    }
}
