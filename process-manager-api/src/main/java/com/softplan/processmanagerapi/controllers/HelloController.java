package com.softplan.processmanagerapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RestController
public class HelloController {
    @GetMapping(path = "/")
    public ResponseEntity home()  {
        return ResponseEntity.ok("Everyone can see me");
    }

    @GetMapping(path = "/screener")
    public ResponseEntity screener() {
        return ResponseEntity.ok("Only USERs can see me");
    }

    @GetMapping(path = "/finisher")
    public ResponseEntity finisher() {
        return ResponseEntity.ok("Only USERs can see me");
    }

    @GetMapping(path = "/admin")
    public ResponseEntity admin()  {
        return ResponseEntity.ok("Only ADMINs can see me");
    }
}
