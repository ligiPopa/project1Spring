package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    //TODO remove it
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/welcome")
    public String index() {
        return "ajax";
    }

}
