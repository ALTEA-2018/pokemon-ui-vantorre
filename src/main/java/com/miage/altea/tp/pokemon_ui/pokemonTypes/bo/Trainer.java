package com.miage.altea.tp.pokemon_ui.pokemonTypes.bo;


import lombok.Data;

import java.util.List;

// TODO
@Data
public class Trainer { 

    private String name; 

    private List<Pokemon> team;

    public Trainer() {
    }

    public Trainer(String name) {
        this.name = name;
    }

    private String password;

}