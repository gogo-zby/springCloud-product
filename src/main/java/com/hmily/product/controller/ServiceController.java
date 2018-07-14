package com.hmily.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

    @GetMapping("/msg")
    public String msg(){
        return "This is product msg!";
    }
}
