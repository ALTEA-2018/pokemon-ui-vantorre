package com.miage.altea.tp.pokemon_ui.pokemonTypes.service;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {
    //TODO 4.5. La page "Mon Profil"


    private RestTemplate restTemplate;
    private String trainerServiceUrl;

    @Autowired
    private PokemonTypeService pokemonTypeService;

    @Override
    public List<Trainer> getTrainers() {
        List<Trainer> list = Arrays.asList(this.restTemplate.getForObject(this.trainerServiceUrl + "/trainers/", Trainer[].class));
        list.forEach(trainer -> trainer.getTeam().forEach(pokemon -> pokemon.setPokemonTypeObject(pokemonTypeService.getPokemonType(pokemon.getPokemonType()))));
        return list;
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
