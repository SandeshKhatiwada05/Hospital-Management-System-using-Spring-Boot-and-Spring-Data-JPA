package com.sandesh.springDataJpaHospitalManagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class HealthController {

    @GetMapping("/health")
    public String Health() {
        return "<h1>Everything is fine<h1>";
    }
}
