package de.materna.trainee.date4u.db.services;

import de.materna.trainee.date4u.db.dto.UnicornDto;
import de.materna.trainee.date4u.db.dto.mapper.UnicornDtoMapper;
import de.materna.trainee.date4u.db.entities.Unicorn;
import de.materna.trainee.date4u.db.repositories.UnicornRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UnicornService {

    private final UnicornRepository repository;
    private final UnicornDtoMapper dtoMapper;

    @Autowired
    public UnicornService(UnicornRepository unicornRepository, UnicornDtoMapper dtoMapper) {
        this.repository = unicornRepository;
        this.dtoMapper = dtoMapper;
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
}
