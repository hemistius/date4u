package de.materna.trainee.date4u.controller.restcontroller;

import de.materna.trainee.date4u.db.dto.ProfileDto;
import de.materna.trainee.date4u.db.services.ProfileService;
import de.materna.trainee.date4u.exceptions.Response404Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/profile")
public class ProfileRestController {

    private final ProfileService service;

    @Autowired
    public ProfileRestController(ProfileService service) {
        this.service = service;
    }

    @GetMapping(path = "{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProfileDto getProfile(@PathVariable String username) {
        return service.findByUsername(username).orElseThrow(Response404Exception::new);
    }
}
