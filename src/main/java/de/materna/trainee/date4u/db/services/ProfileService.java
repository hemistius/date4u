package de.materna.trainee.date4u.db.services;

import de.materna.trainee.date4u.db.dto.ProfileDto;
import de.materna.trainee.date4u.db.dto.mapper.ProfileDtoMapper;
import de.materna.trainee.date4u.db.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
