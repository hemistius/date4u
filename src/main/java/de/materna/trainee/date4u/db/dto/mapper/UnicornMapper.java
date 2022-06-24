package de.materna.trainee.date4u.db.dto.mapper;

import de.materna.trainee.date4u.db.entities.Unicorn;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UnicornMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUnicornIgnoreNull(Unicorn sourceUnicorn, @MappingTarget Unicorn targetUnicorn);

    void updateUnicorn(Unicorn sourceUnicorn, @MappingTarget Unicorn targetUnicorn);
}
