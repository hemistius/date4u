package de.materna.trainee.date4u.controller.restcontroller;

import de.materna.trainee.date4u.db.dto.RegisterRequestDto;
import de.materna.trainee.date4u.db.dto.UnicornDto;
import de.materna.trainee.date4u.db.services.PhotoService;
import de.materna.trainee.date4u.db.services.UnicornService;
import de.materna.trainee.date4u.exceptions.Response400Exception;
import de.materna.trainee.date4u.exceptions.Response404Exception;
import de.materna.trainee.date4u.security.UnicornUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/rest/unicorn")
public class UnicornRestController {

    private final UnicornService service;
    private final PhotoService photoService;

    @Autowired
    public UnicornRestController(UnicornService service, PhotoService photoService) {
        this.service = service;
        this.photoService = photoService;
    }

    @PostMapping(value = "register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUnicorn(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        try {
            UnicornDto unicorn = service.createUnicorn(registerRequestDto.getUnicorn());
            photoService.savePhoto(unicorn.getProfile(), registerRequestDto.getEncodedImage(), true);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException | IOException ex) {
            throw new Response400Exception(ex);
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editUnicorn(@RequestBody UnicornDto unicorn) {
        try {
            UnicornUserDetails user = (UnicornUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UnicornDto unicornDto = service.findUnicornByMail(user.getUsername()).orElseThrow(Response404Exception::new);
            unicorn.setId(unicornDto.getId());
            service.updateUnicorn(unicorn);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (IllegalArgumentException ex) {
            throw new Response400Exception(ex);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
