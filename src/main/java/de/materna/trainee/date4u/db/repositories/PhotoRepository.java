package de.materna.trainee.date4u.db.repositories;

import de.materna.trainee.date4u.db.entities.Photo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PhotoRepository extends CrudRepository<Photo, Long> {

    Optional<Photo> findFirstByOrderByNameDesc();
}
