package de.materna.trainee.date4u.db.dto.mapper;

import de.materna.trainee.date4u.db.dto.UnicornDto;
import de.materna.trainee.date4u.db.entities.Unicorn;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UnicornDtoMapper {

    UnicornDto toDto(Unicorn unicorn, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @DoIgnore
    default UnicornDto toDto(Unicorn unicorn) {
        return toDto(unicorn, new CycleAvoidingMappingContext());
    }

    Unicorn fromDto(UnicornDto unicornDto, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @DoIgnore
    default Unicorn fromDto(UnicornDto unicornDto) {
        return fromDto(unicornDto, new CycleAvoidingMappingContext());
    }
}
