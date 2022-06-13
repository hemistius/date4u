package de.materna.trainee.date4u.controller;

import de.materna.trainee.date4u.db.dto.UnicornDto;
import de.materna.trainee.date4u.db.services.UnicornService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class TestRestController {

    private final UnicornService unicornService;

    @Autowired
    public TestRestController(UnicornService unicornService) {
        this.unicornService = unicornService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UnicornDto> getAll() {
        return unicornService.findAll();
    }
}
