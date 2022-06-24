package de.materna.trainee.date4u.db.repositories;

import de.materna.trainee.date4u.db.entities.Unicorn;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UnicornRepository extends PagingAndSortingRepository<Unicorn, Long> {

    @EntityGraph(value = "unicorn.all")
    List<Unicorn> findAll();
    @Override
    @EntityGraph(value = "unicorn.all")
    Optional<Unicorn> findById(Long id);

    @EntityGraph(value = "unicorn.sparse")
    @Query("from Unicorn u where lower(u.email) = lower(:email) ")
    Optional<Unicorn> findUnicornByEmailIgnoreCaseSparse(@Param("email") String email);

    @EntityGraph(value = "unicorn.sparse")
    Optional<Unicorn> findUnicornByEmailIgnoreCase(String email);
}
