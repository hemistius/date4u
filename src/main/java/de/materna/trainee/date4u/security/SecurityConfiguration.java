package de.materna.trainee.date4u.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
            .mvcMatchers("/rest/unicorn/register", "/rest/photo/upload")
            .permitAll()
            .mvcMatchers("/rest/**", "/profile", "/search", "/")
            .authenticated()
            .mvcMatchers("/js/**", "/img/login/**", "/img/logo/**", "/register")
            .permitAll()
            .mvcMatchers("/img/**")
            .authenticated()
            .anyRequest()
            .denyAll()
            .and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/perform_login")
            .defaultSuccessUrl("/search")
            .permitAll()
            .and()
            .httpBasic()
            .and()
            .logout()
            .logoutUrl("/perform_logout")
            .logoutSuccessUrl("/login")
            .permitAll()
            .deleteCookies("JSESSIONID")
            .clearAuthentication(true)
            .invalidateHttpSession(true);

        http.cors().and().csrf().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return "{noop}%s".formatted(rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                String cleaned = StringUtils.removeStart(encodedPassword, "{noop}");
                return rawPassword.toString().equals(cleaned);
            }
        };
    }
}
