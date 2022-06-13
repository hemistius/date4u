package de.materna.trainee.date4u.db.dto.mapper;

import de.materna.trainee.date4u.db.dto.ProfileDto;
import de.materna.trainee.date4u.db.entities.Profile;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileDtoMapper {

    ProfileDto toDto(Profile profile, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @DoIgnore
    default ProfileDto toDto(Profile profile) {
        return toDto(profile, new CycleAvoidingMappingContext());
    }

    Profile fromDto(ProfileDto profileDto, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @DoIgnore
    default Profile fromDto(ProfileDto profileDto) {
        return fromDto(profileDto, new CycleAvoidingMappingContext());
    }
}
