package de.materna.trainee.date4u.controller.restcontroller;

import de.materna.trainee.date4u.db.services.PhotoService;
import de.materna.trainee.date4u.exceptions.Response400Exception;
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

@RestController
@RequestMapping("/rest/photo")
public class PhotoRestController {

    private final PhotoService photoService;

    @Autowired
    public PhotoRestController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadPhoto(@RequestParam("file") MultipartFile file) {
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (file.getOriginalFilename() == null || !file.getOriginalFilename().toLowerCase(Locale.ROOT).endsWith(".jpg")) {
            throw new Response400Exception("Only jpg files are allowed");
        }
        try {
            photoService.savePhoto(null, file, false);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }
}
