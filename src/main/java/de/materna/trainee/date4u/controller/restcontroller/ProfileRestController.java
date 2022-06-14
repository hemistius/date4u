package de.materna.trainee.date4u.controller.restcontroller;

import de.materna.trainee.date4u.db.dto.ProfileDto;
import de.materna.trainee.date4u.db.dto.QueryRangesDto;
import de.materna.trainee.date4u.db.services.ProfileService;
import de.materna.trainee.date4u.exceptions.Response404Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/profile")
public class ProfileRestController {

    private final ProfileService service;

    @Autowired
    public ProfileRestController(ProfileService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ProfileDto getProfile(@RequestParam("username") String username) {
        return service.findByUsername(username).orElseThrow(Response404Exception::new);
    }

    @GetMapping("/query")
    public List<ProfileDto> queryProfiles(@RequestParam("minAge") Optional<Integer> minAge,
                                          @RequestParam("maxAge") Optional<Integer> maxAge,
                                          @RequestParam("minLength") Optional<Short> minLength,
                                          @RequestParam("maxLength") Optional<Short> maxLength,
                                          @RequestParam(value = "male", defaultValue = "true") boolean male,
                                          @RequestParam(value = "female", defaultValue = "true") boolean female,
                                          @RequestParam(value = "diverse", defaultValue = "true") boolean diverse
    ) {
        return service.queryProfilesSparse(
                minAge.orElse(18),
                maxAge.orElse(200),
                minLength.orElse((short) 0),
                maxLength.orElse(Short.MAX_VALUE),
                male,
                female,
                diverse
        );
    }

    @GetMapping("/query/ranges")
    public QueryRangesDto getQueryRanges() {
        return service.getQueryRanges();
    }
}
