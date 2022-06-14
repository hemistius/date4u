package de.materna.trainee.date4u.controller;

import de.materna.trainee.date4u.db.services.ProfileService;
import de.materna.trainee.date4u.exceptions.Response404Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    private final ProfileService profileService;

    @Autowired
    public WebController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profile")
    public String getProfile(@RequestParam String username) {
        if (profileService.countByUsernameIgnoringCase(username) <= 0) {
            throw new Response404Exception();
        }
        return "profile";
    }
}
