package com.miage.altea.tp.pokemon_ui.pokemonTypes.bo;

import lombok.Data;

import java.util.List;

@Data
public class PokemonType {

    private int id;
    private int baseExperience;
    private int height;
    private String name;
    private Sprites sprites;
    private Stats stats;
    private int weight;
    private List<String> types;

}