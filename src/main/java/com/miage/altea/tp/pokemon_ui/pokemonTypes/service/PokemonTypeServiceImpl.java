package com.miage.altea.tp.pokemon_ui.pokemonTypes.service;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    private RestTemplate restTemplate;
    private String pokemonServiceUrl;

    @Cacheable("pokemon-types")
    public List<PokemonType> listPokemonsTypes() {
        Locale locale = LocaleContextHolder.getLocale();
        return Arrays.asList(this.restTemplate.getForObject(this.pokemonServiceUrl + "/pokemon-types?locale=" + locale, PokemonType[].class));
    }



    @Cacheable("pokemon-types")
    public PokemonType getPokemonType(int id) {
        Locale locale = LocaleContextHolder.getLocale();
        return this.restTemplate.getForObject(this.pokemonServiceUrl + "/pokemon-types/{id}", PokemonType.class, id );
    }

    @Autowired
    @Qualifier("restTemplate")
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${pokemonType.service.url}")
    public void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl = pokemonServiceUrl;
    }
}