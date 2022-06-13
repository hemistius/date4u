package de.materna.trainee.date4u.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class TestController {

    @GetMapping("/index")
    fun testSite(): String {
        return "index"
    }
}