package de.materna.trainee.date4u.db.dto.mapper;

import de.materna.trainee.date4u.db.dto.PhotoDto;
import de.materna.trainee.date4u.db.entities.Photo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhotoDtoMapper {

    PhotoDto toDto(Photo photo);

    Photo fromDto(PhotoDto photoDto);
}
