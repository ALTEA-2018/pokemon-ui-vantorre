package com.miage.altea.tp.pokemon_ui.config;


import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.Trainer;
import com.miage.altea.tp.pokemon_ui.pokemonTypes.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private TrainerService trainerService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.csrf().disable();
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return s -> {
            Trainer trainer = trainerService.getTrainer(s);
            if (trainer == null) throw new BadCredentialsException("No such user");
            return new User(trainer.getName(), trainer.getPassword(), true, true, true, true, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        };
    }

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }
}