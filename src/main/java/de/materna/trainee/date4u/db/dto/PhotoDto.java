package de.materna.trainee.date4u.db.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude="profile")
public class PhotoDto {

    private Long id;
    private String name;
    private boolean isProfilePhoto;
    private LocalDateTime created;
    @JsonBackReference
    private ProfileDto profile;
}
