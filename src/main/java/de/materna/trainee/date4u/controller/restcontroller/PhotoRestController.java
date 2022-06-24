package de.materna.trainee.date4u.controller.restcontroller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/rest/photo")
public class PhotoRestController {

    private final PhotoService photoService;
    private final UnicornService unicornService;

    @Autowired
    public PhotoRestController(PhotoService photoService, UnicornService unicornService) {
        this.photoService = photoService;
        this.unicornService = unicornService;
    }

    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadPhoto(@RequestParam("file") MultipartFile file) {
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (file.getOriginalFilename() == null || !file.getOriginalFilename().toLowerCase(Locale.ROOT).endsWith(".jpg")) {
            throw new Response400Exception("Only jpg files are allowed");
        }
        try {
            UnicornUserDetails user = (UnicornUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Optional<UnicornDto> unicornDto = unicornService.findUnicornByMail(user.getUsername());
            photoService.savePhoto(unicornDto.orElseThrow(Response404Exception::new).getProfile(), file, false);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }
}
