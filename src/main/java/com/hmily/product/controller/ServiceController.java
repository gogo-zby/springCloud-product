package com.hmily.product.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ServiceController {

    @GetMapping("/msg")
    public String msg(){
        return "This is product msg!";
    }

    @GetMapping("/msg2")
    public String msg2(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error("Thread.sleep: {}", e);
        }
        return "This is product msg2!";
    }
}
