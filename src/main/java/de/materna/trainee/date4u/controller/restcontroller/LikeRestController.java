package de.materna.trainee.date4u.controller.restcontroller;

import de.materna.trainee.date4u.db.dto.LikedDto;
import de.materna.trainee.date4u.db.dto.UnicornDto;
import de.materna.trainee.date4u.db.services.LikesService;
import de.materna.trainee.date4u.db.services.UnicornService;
import de.materna.trainee.date4u.exceptions.Response400Exception;
import de.materna.trainee.date4u.security.UnicornUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rest/like")
public class LikeRestController {

    private final LikesService likesService;
    private final UnicornService unicornService;

    @Autowired
    public LikeRestController(LikesService likesService, UnicornService unicornService) {
        this.likesService = likesService;
        this.unicornService = unicornService;
    }

    @GetMapping
    public ResponseEntity<LikedDto> getLiked(@RequestParam("username") String likee) {
        UnicornUserDetails user = (UnicornUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UnicornDto> unicornDto = unicornService.findUnicornByMail(user.getUsername());
        LikedDto likedDto = new LikedDto(likesService.hasLiked(
                unicornDto.orElseThrow(Response400Exception::new).getProfile().getNickname(),
                likee
        ));
        return ResponseEntity.ok(likedDto);
    }

    @PutMapping
    public ResponseEntity<?> setLiked(@RequestParam("username") String likee) {
        UnicornUserDetails user = (UnicornUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UnicornDto> unicornDto = unicornService.findUnicornByMail(user.getUsername());
        likesService.setLiked(
                unicornDto.orElseThrow(Response400Exception::new).getProfile().getNickname(),
                likee
        );
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteLiked(@RequestParam("username") String likee) {
        UnicornUserDetails user = (UnicornUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UnicornDto> unicornDto = unicornService.findUnicornByMail(user.getUsername());
        likesService.removeLiked(
                unicornDto.orElseThrow(Response400Exception::new).getProfile().getNickname(),
                likee
        );
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
