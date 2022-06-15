package de.materna.trainee.date4u.controller;

import de.materna.trainee.date4u.db.dto.UnicornDto;
import de.materna.trainee.date4u.db.services.ProfileService;
import de.materna.trainee.date4u.db.services.UnicornService;
import de.materna.trainee.date4u.exceptions.Response404Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Optional;

@Controller
public class WebController {

    private final ProfileService profileService;
    private final UnicornService unicornService;

    @Autowired
    public WebController(ProfileService profileService, UnicornService unicornService) {
        this.profileService = profileService;
        this.unicornService = unicornService;
    }

    @GetMapping("/profile")
    public String getProfile(@RequestParam Optional<String> username, Principal principal) {
        if(username.isEmpty()){
            Optional<UnicornDto> unicornByMail = unicornService.findUnicornByMail(principal.getName());
//            return unicornByMail.map(unicornDto -> getProfile(Optional.of(unicornDto.getProfile().getNickname()), principal)).orElseThrow(Response404Exception::new);
            return unicornByMail.map(unicornDto -> "redirect:/profile?username=" + unicornDto.getProfile().getNickname()).orElseThrow(Response404Exception::new);
        }
        if (profileService.countByUsernameIgnoringCase(username.get()) <= 0) {
            throw new Response404Exception();
        }
        String name = principal.getName();
        return "profile";
    }

    @GetMapping("/search")
    public String getProfile() {
        return "search";
    }
}
