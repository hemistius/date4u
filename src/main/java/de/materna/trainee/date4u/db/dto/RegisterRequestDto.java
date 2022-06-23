package de.materna.trainee.date4u.db.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RegisterRequestDto {

    @NotNull
    @Valid
    private UnicornDto unicorn;
    @NotEmpty(message = "Profilbild muss angegeben werden")
    private String encodedImage;
}
