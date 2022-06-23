package de.materna.trainee.date4u.db.services;

import de.materna.trainee.date4u.db.dto.PhotoDto;
import de.materna.trainee.date4u.db.dto.ProfileDto;
import de.materna.trainee.date4u.db.dto.mapper.PhotoDtoMapper;
import de.materna.trainee.date4u.db.entities.Photo;
import de.materna.trainee.date4u.db.repositories.PhotoRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final PhotoDtoMapper mapper;
    private final ProfileService profileService;

    @Autowired
    public PhotoService(PhotoRepository photoRepository, PhotoDtoMapper mapper, ProfileService profileService) {
        this.photoRepository = photoRepository;
        this.mapper = mapper;
        this.profileService = profileService;
    }

    public void savePhoto(ProfileDto profileDto, MultipartFile file, boolean isProfilePhoto) throws IOException {
        String nextName = findNextName();
        Path savePath = Paths.get("src/main/resources/static/img/profile", nextName);
        file.transferTo(savePath);
        PhotoDto photoDto = new PhotoDto();
        photoDto.setName(StringUtils.removeEnd(nextName, ".jpg"));
        photoDto.setProfilePhoto(isProfilePhoto);
        createPhotoDbObject(profileDto, photoDto);
    }

    public void savePhoto(ProfileDto profileDto, String base64EncodedImage, boolean isProfilePhoto) throws IOException {
        byte[] decodedImage = Base64Utils.decodeFromString(base64EncodedImage);
        String nextName = findNextName();
        Path savePath = Paths.get("src/main/resources/static/img/profile", nextName);
        try (OutputStream out = new FileOutputStream(savePath.toFile())) {
            out.write(decodedImage);
        }
        PhotoDto photoDto = new PhotoDto();
        photoDto.setName(StringUtils.removeEnd(nextName, ".jpg"));
        photoDto.setProfilePhoto(isProfilePhoto);
        createPhotoDbObject(profileDto, photoDto);
    }

    private void createPhotoDbObject(ProfileDto profileDto, PhotoDto photoDto) {
        Optional<ProfileDto> profileOptional = profileService.findByUsername(profileDto.getNickname());
        if (profileOptional.isPresent()){
            ProfileDto profile = profileOptional.get();
            photoDto.setProfile(profile);
            profile.getPhotos().add(photoDto);
            profileService.update(profile);
        }

    }

    private String findNextName() {
        Optional<Photo> photo = photoRepository.findFirstByOrderByNameDesc();
        Pattern pattern = Pattern.compile("\\d\\d\\d");
        String photoNumberStr = photo.map(Photo::getName)
                                     .map(pattern::matcher)
                                     .stream()
                                     .flatMap(Matcher::results)
                                     .findFirst()
                                     .map(MatchResult::group)
                                     .orElseGet(() -> "000");
        int photoNumber = Integer.parseInt(photoNumberStr) + 1;
        return "unicorn%03d.jpg".formatted(photoNumber);
    }
}
