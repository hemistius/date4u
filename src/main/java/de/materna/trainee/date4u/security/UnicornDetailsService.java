package de.materna.trainee.date4u.security;

import de.materna.trainee.date4u.db.dto.UnicornDto;
import de.materna.trainee.date4u.db.services.UnicornService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnicornDetailsService implements UserDetailsService {

    private final UnicornService unicornService;

    @Autowired
    public UnicornDetailsService(UnicornService unicornService) {
        this.unicornService = unicornService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UnicornDto> unicornByMail = unicornService.findUnicornByMailSparse(username);
        return unicornService.findUnicornByMailSparse(username)
                             .map(UnicornUserDetails::new)
                             .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
