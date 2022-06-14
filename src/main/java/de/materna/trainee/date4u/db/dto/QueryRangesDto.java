package de.materna.trainee.date4u.db.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class QueryRangesDto {

    private LocalDate minBirthdate;
    private LocalDate maxBirthdate;
    private int minLength;
    private int maxLength;
}
