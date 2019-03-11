package com.miage.altea.tp.pokemon_ui.pokemonTypes.service;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.PokemonType;
import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class TrainerServiceImpl implements TrainerService {
    //TODO 4.5. La page "Mon Profil"



    private RestTemplate restTemplate;
    private String trainerServiceUrl;

    @Override
    public List<Trainer> getTrainers() {
        return Arrays.asList(this.restTemplate.getForObject(this.trainerServiceUrl + "/trainers/", Trainer[].class));
    }

    @Override
    public Trainer getTrainer(String s) {
        return this.restTemplate.getForObject(this.trainerServiceUrl + "/trainers/" + s, Trainer.class);
    }

    @Autowired
    @Qualifier("trainerApiRestTemplate")
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${trainer.service.url}")
    void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.trainerServiceUrl = pokemonServiceUrl;
    }
}
