package de.materna.trainee.date4u.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/index")
    public String testSite() {
        return "index";
    }
}
