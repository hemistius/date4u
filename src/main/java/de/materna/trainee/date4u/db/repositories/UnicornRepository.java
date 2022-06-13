package de.materna.trainee.date4u.db.repositories;

import de.materna.trainee.date4u.db.entities.Unicorn;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UnicornRepository extends PagingAndSortingRepository<Unicorn, Long> {

    @EntityGraph(value = "unicorn.all")
    List<Unicorn> findAll();
}
