package de.materna.trainee.date4u.db.repositories;

import de.materna.trainee.date4u.db.entities.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends PagingAndSortingRepository<Profile, Long> {

    long countByNicknameIgnoreCase(String nickname);

    Optional<Profile> findByNicknameIgnoreCase(String nickname);

    @Query("from Profile p where (p.birthdate between :minBirthdate and :maxBirthdate) and (p.hornlength between  :minLength and :maxLength) and p.gender in :genders")
    @EntityGraph(value = "profile.all")
    List<Profile> queryProfiles(@Param("minBirthdate") LocalDate minBirthdate, @Param("maxBirthdate") LocalDate maxBirthdate, @Param("minLength") short minLength, @Param("maxLength") short maxLength, @Param("genders") Collection<Byte> genders);

    @Query("select min(p.birthdate) from Profile p")
    LocalDate findMinBirthdate();

    @Query("select max(p.birthdate) from Profile p")
    LocalDate findMaxBirthdate();

    @Query("select min(p.hornlength) from Profile p")
    int findMinHornlength();

    @Query("select max(p.hornlength) from Profile p")
    int findMaxHornlength();
}
