package com.brotech.auth.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping(path = "/test/")
public class AuthTestController {


    @GetMapping("get")
    public ResponseEntity<?> getData() {
        return ResponseEntity.ok("yep working fine");
    }
}
