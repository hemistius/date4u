package de.materna.trainee.date4u.db.dto;

import de.materna.trainee.date4u.db.dto.validation.Adult;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class ProfileDto {

    private Long id;
    @NotEmpty(message = "Nickname darf nicht null sein")
    private String nickname;
    @Adult(message = "Alter muss mindestens 18 Jahre sein")
    private LocalDate birthdate;
    @PositiveOrZero
    @Max(value = 50, message = "Hornlänge darf 50cm nicht überschreiten.")
    private short hornlength;
    @PositiveOrZero
    @Max(2)
    private byte gender;
    @PositiveOrZero
    @Max(2)
    private Byte attractedToGender;
    private String description;
    private LocalDateTime lastSeen;
    private Set<PhotoDto> photos;
}
