package de.materna.trainee.date4u.db.repositories;

import de.materna.trainee.date4u.db.entities.Likes;
import de.materna.trainee.date4u.db.entities.LikesKey;
import de.materna.trainee.date4u.db.entities.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LikesRepository extends CrudRepository<Likes, LikesKey> {

    boolean existsByLikeeAndLiker(Profile likee, Profile liker);

    Optional<Likes> findByLikeeAndAndLiker(Profile likee, Profile liker);
}
