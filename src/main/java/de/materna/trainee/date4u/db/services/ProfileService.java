package de.materna.trainee.date4u.db.services;

import de.materna.trainee.date4u.db.dto.ProfileDto;
import de.materna.trainee.date4u.db.dto.QueryRangesDto;
import de.materna.trainee.date4u.db.dto.mapper.ProfileDtoMapper;
import de.materna.trainee.date4u.db.entities.Profile;
import de.materna.trainee.date4u.db.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProfileService {

    private final ProfileRepository repository;
    private final ProfileDtoMapper mapper;

    @Autowired
    public ProfileService(ProfileRepository repository, ProfileDtoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public long countByUsernameIgnoringCase(String username) {
        return repository.countByNicknameIgnoreCase(username);
    }

    public Optional<ProfileDto> findByUsername(String username) {
        return repository.findByNicknameIgnoreCase(username).map(mapper::toDto);
    }

    public List<ProfileDto> queryProfilesSparse(int minAge,
                                                int maxAge,
                                                short minLength,
                                                short maxLength,
                                                boolean male,
                                                boolean female,
                                                boolean diverse) {
        LocalDate maxDate = LocalDate.now().minus(Period.ofYears(minAge));
        LocalDate minDate = LocalDate.now().minus(Period.ofYears(maxAge));
        Set<Byte> genders = new HashSet<>();
        if (male) {
            genders.add((byte) 0);
        }
        if (female) {
            genders.add((byte) 1);
        }
        if (diverse) {
            genders.add((byte) 2);//TODO enum
        }

        return repository.queryProfiles(minDate, maxDate, minLength, maxLength, genders)
                         .stream()
                         .map(mapper::toDto)
                         .toList();
    }

    public QueryRangesDto getQueryRanges() {
        return new QueryRangesDto(
                repository.findMinBirthdate(),
                repository.findMaxBirthdate(),
                repository.findMinHornlength(),
                repository.findMaxHornlength()
        );
    }

    public ProfileDto update(ProfileDto profileDto) {
        Profile profile = mapper.fromDto(profileDto);
        return mapper.toDto(repository.save(profile));
    }
}
