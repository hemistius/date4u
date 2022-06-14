package de.materna.trainee.date4u.db.repositories;

import de.materna.trainee.date4u.db.entities.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProfileRepository extends PagingAndSortingRepository<Profile, Long> {

    long countByNicknameIgnoreCase(String nickname);

    Optional<Profile> findByNicknameIgnoreCase(String nickname);
}
