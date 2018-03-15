package com.kmejka.client.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnsecureController {

    @GetMapping("/api/unsecure/info")
    public ResponseEntity<String> info() {
        return ResponseEntity.ok("Hello world");
    }
}
