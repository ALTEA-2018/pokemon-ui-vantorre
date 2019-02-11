package com.miage.altea.tp.pokemon_ui.controller;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.service.PokemonTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PokemonTypeController {

    private PokemonTypeService pokemonTypeService;

    @GetMapping("/pokedex")
    public ModelAndView pokedex() {
        ModelAndView res = new ModelAndView();
        res.addObject("pokemonTypes", this.pokemonTypeService.listPokemonsTypes());
        res.setViewName("pokedex");
        return res;
    }

    public void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }
}