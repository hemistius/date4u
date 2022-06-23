package de.materna.trainee.date4u.db.services;

import de.materna.trainee.date4u.db.entities.Likes;
import de.materna.trainee.date4u.db.entities.Profile;
import de.materna.trainee.date4u.db.repositories.LikesRepository;
import de.materna.trainee.date4u.db.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LikesService {

    private final ProfileRepository profileRepository;
    private final LikesRepository likesRepository;

    @Autowired
    public LikesService(ProfileRepository profileRepository, LikesRepository likesRepository) {
        this.profileRepository = profileRepository;
        this.likesRepository = likesRepository;
    }

    public boolean hasLiked(String likerUsername, String likeeUsername) {
        Optional<Profile> likee = profileRepository.findByNicknameIgnoreCase(likeeUsername);
        Optional<Profile> liker = profileRepository.findByNicknameIgnoreCase(likerUsername);

        return likee.isPresent() && liker.isPresent() && likesRepository.existsByLikeeAndLiker(likee.get(), liker.get());
    }

    public void setLiked(String likerUsername, String likeeUsername) {
        Optional<Profile> likee = profileRepository.findByNicknameIgnoreCase(likeeUsername);
        Optional<Profile> liker = profileRepository.findByNicknameIgnoreCase(likerUsername);

        Optional<Likes> likes = likesRepository.findByLikeeAndAndLiker(
                likee.orElseThrow(NoSuchElementException::new),
                liker.orElseThrow(NoSuchElementException::new)
        );
        if (likes.isEmpty()) {
            likesRepository.save(new Likes(liker.get(), likee.get()));
        }
    }

    public void removeLiked(String likerUsername, String likeeUsername) {
        Optional<Profile> likee = profileRepository.findByNicknameIgnoreCase(likeeUsername);
        Optional<Profile> liker = profileRepository.findByNicknameIgnoreCase(likerUsername);

        likesRepository.findByLikeeAndAndLiker(
                likee.orElseThrow(NoSuchElementException::new),
                liker.orElseThrow(NoSuchElementException::new)
        ).ifPresent(likesRepository::delete);
    }
}
