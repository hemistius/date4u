package de.materna.trainee.date4u.db.services;

import de.materna.trainee.date4u.db.dto.UnicornDto;
import de.materna.trainee.date4u.db.dto.mapper.UnicornDtoMapper;
import de.materna.trainee.date4u.db.dto.mapper.UnicornMapper;
import de.materna.trainee.date4u.db.entities.Profile;
import de.materna.trainee.date4u.db.entities.Unicorn;
import de.materna.trainee.date4u.db.repositories.UnicornRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class UnicornService {

    private final UnicornRepository repository;
    private final UnicornDtoMapper dtoMapper;
    private final PasswordEncoder encoder;
    private final UnicornMapper unicornMapper;

    @Autowired
    public UnicornService(UnicornRepository unicornRepository, UnicornDtoMapper dtoMapper, PasswordEncoder encoder, UnicornMapper unicornMapper) {
        this.repository = unicornRepository;
        this.dtoMapper = dtoMapper;
        this.encoder = encoder;
        this.unicornMapper = unicornMapper;
    }

    public List<UnicornDto> findAll() {
        return repository.findAll()
                         .stream()
                         .map(dtoMapper::toDto)
                         .toList();
    }

    public Optional<UnicornDto> findUnicornByMailSparse(String email) {
        return repository.findUnicornByEmailIgnoreCaseSparse(email).map(dtoMapper::toDtoSparse);
    }

    public Optional<UnicornDto> findUnicornByMail(String email) {
        return repository.findUnicornByEmailIgnoreCase(email).map(dtoMapper::toDto);
    }

    public UnicornDto createUnicorn(UnicornDto unicornDto) {
        Unicorn unicorn = dtoMapper.fromDto(unicornDto);
        if (unicorn.getId() != null) {
            throw new IllegalArgumentException("Id should not be set");
        }
        unicorn.setPassword(encoder.encode(unicorn.getPassword()));
        return dtoMapper.toDto(repository.save(unicorn));
    }

    public UnicornDto updateUnicorn(UnicornDto unicornDto){
        Unicorn unicorn = dtoMapper.fromDto(unicornDto);
        if (unicorn.getId() == null){
            throw new IllegalArgumentException("cannot update entity with an id of null");
        }
        Unicorn oldUnicorn = repository.findById(unicorn.getId()).orElseThrow(NoSuchElementException::new);
        Profile profile = oldUnicorn.getProfile();
        String pr = profile.getNickname();
        unicornMapper.updateUnicornIgnoreNull(unicorn, oldUnicorn);
        System.out.printf("LOL");
        return null;
    }
}
