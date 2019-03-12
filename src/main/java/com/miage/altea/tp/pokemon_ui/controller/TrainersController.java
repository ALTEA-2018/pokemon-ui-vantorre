package com.miage.altea.tp.pokemon_ui.controller;

import com.miage.altea.tp.pokemon_ui.pokemonTypes.bo.Trainer;
import com.miage.altea.tp.pokemon_ui.pokemonTypes.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TrainersController {

    private TrainerService trainerService;

    @GetMapping("/trainers")
    public ModelAndView pokedex() {
        ModelAndView res = new ModelAndView();
        res.addObject("trainers", this.trainerService.getTrainers());
        res.setViewName("trainers");
        return res;
    }

    @GetMapping("/profile")
    public ModelAndView profile() {
        ModelAndView res = new ModelAndView();
        Trainer currentTrainer = this.trainerService.getTrainer(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        res.addObject("trainer", currentTrainer);
        res.setViewName("profile");
        return res;
    }

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }
}