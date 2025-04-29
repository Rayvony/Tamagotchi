package com.tallerwebi.controller;

import com.tallerwebi.models.Pet;
import com.tallerwebi.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetService petService;

    @GetMapping("/{id}")
    public String getPet(@PathVariable Long id, Model model) {
        Pet pet = petService.getPetById(id);
        model.addAttribute("pet", pet);
        return "pet"; // Vista Thymeleaf
    }

    @PostMapping("/{id}/feed")
    public String feedPet(@PathVariable Long id) {
        petService.feedPet(id);
        return "redirect:/pet/" + id;
    }

    @PostMapping("/{id}/play")
    public String playWithPet(@PathVariable Long id) {
        petService.playWithPet(id);
        return "redirect:/pet/" + id;
    }

    // Otros endpoints: dormir, curar, etc.
}
