package com.miage.altea.tp.pokemon_ui.pokemonTypes.bo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Pokemon {

    private int id;

    private int pokemonType;

    @JsonIgnore
    private PokemonType pokemonTypeObject;

    private int level;

    public Pokemon() {
    }

    public Pokemon(int pokemonType, int level) {
        this.pokemonType = pokemonType;
        this.level = level;
    }

}