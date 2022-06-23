package de.materna.trainee.date4u.db.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class UnicornDto {

    private Long id;
    @Email(message = "E-Mail muss valide sein.")
    private String email;
    @NotEmpty(message = "Passwort darf nicht leer sein")
    private String password;
    @Valid
    private ProfileDto profile;
}
