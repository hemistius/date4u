package de.materna.trainee.date4u.db.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UnicornDto {

    private Long id;
    private String email;
    private String password;
    private ProfileDto profile;
}
