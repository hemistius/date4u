package de.materna.trainee.date4u.db.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class ProfileDto {

    private Long id;
    private String nickname;
    private LocalDate birthdate;
    private short hornlength;
    private byte gender;
    private Byte attractedToGender;
    private String description;
    private LocalDateTime lastSeen;
    private Set<PhotoDto> photos;
}
