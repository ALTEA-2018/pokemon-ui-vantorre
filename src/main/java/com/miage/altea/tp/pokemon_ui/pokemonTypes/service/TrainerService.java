package com.miage.altea.tp.pokemon_ui.pokemonTypes.service;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.Trainer;

import java.util.List;

public interface TrainerService {
    Trainer getTrainer(String s);

    List<Trainer> getTrainers();
}
