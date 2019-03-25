package com.miage.altea.tp.pokemon_ui.pokemonTypes.service;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Retryable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerServiceImpl implements TrainerService {
    //TODO 4.5. La page "Mon Profil"


    private RestTemplate restTemplate;
    private String trainerServiceUrl;

    @Autowired
    private PokemonTypeService pokemonTypeService;

    @Override
    @Cacheable(value = "trainers")
    @Retryable
    public List<Trainer> getTrainers() {
        List<Trainer> list = Arrays.asList(this.restTemplate.getForObject(this.trainerServiceUrl + "/trainers/", Trainer[].class));
        list = list.stream().filter(trainer -> !trainer.getName().equals(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername())).collect(Collectors.toList());
        list.forEach(trainer -> trainer.getTeam().forEach(pokemon -> pokemon.setPokemonTypeObject(pokemonTypeService.getPokemonType(pokemon.getPokemonType()))));
        return list;
    }

    @Override
    @Cacheable("trainers")
    @Retryable
    public Trainer getTrainer(String s) {
        Trainer trainer = this.restTemplate.getForObject(this.trainerServiceUrl + "/trainers/" + s, Trainer.class);
        trainer.getTeam().forEach(pokemon -> pokemon.setPokemonTypeObject(pokemonTypeService.getPokemonType(pokemon.getPokemonType())));
        return trainer;
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
