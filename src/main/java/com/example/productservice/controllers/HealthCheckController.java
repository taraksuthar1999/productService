package com.example.productservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/products")
public class HealthCheckController {
        @GetMapping("/health")
        public ResponseEntity<String> healthCheck(){
            return ResponseEntity.ok("I am healthy");
        }
}
