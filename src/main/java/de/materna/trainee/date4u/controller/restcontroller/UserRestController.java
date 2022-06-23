package de.materna.trainee.date4u.controller.restcontroller;

import de.materna.trainee.date4u.db.dto.UnicornDto;
import de.materna.trainee.date4u.db.dto.mapper.UnicornDtoMapper;
import de.materna.trainee.date4u.db.services.UnicornService;
import de.materna.trainee.date4u.exceptions.Response404Exception;
import de.materna.trainee.date4u.security.UnicornUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/rest/user")
public class UserRestController {

    private final UnicornService unicornService;
    private final UnicornDtoMapper dtoMapper;

    @Autowired
    public UserRestController(UnicornService unicornService, UnicornDtoMapper dtoMapper) {
        this.unicornService = unicornService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping(value = "/username", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getUsernameLoggedIn() {
        UnicornUserDetails user = (UnicornUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UnicornDto> unicornDto = unicornService.findUnicornByMail(user.getUsername());
        return unicornDto.orElseThrow(Response404Exception::new).getProfile().getNickname();
    }

    @GetMapping(value = "/userdetails")
    public UnicornDto getUserDetails() {
        UnicornUserDetails user = (UnicornUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UnicornDto> unicornDto = unicornService.findUnicornByMail(user.getUsername());
        return unicornDto.map(dtoMapper::sanitize).orElseThrow(Response404Exception::new);
    }

}
