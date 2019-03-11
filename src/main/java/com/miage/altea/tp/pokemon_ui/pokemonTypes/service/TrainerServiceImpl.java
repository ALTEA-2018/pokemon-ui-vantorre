package com.miage.altea.tp.pokemon_ui.pokemonTypes.service;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.Trainer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {
    @Override
    public Trainer getTrainer(String s) {
        //TODO
        return Trainer.builder().name("trainer").build();
    }

    @Override
    public List<Trainer> getTrainers() {
        //TODO
        List<Trainer> list = new ArrayList<>();
        list.add(Trainer.builder().name("trainer").build());
        return list;
    }
    //TODO 4.5. La page "Mon Profil"
}
