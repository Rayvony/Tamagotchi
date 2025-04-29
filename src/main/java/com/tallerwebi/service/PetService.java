package com.tallerwebi.service;

import com.tallerwebi.models.Pet;
import com.tallerwebi.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    public Pet feedPet(Long petId) {
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new RuntimeException("Pet not found"));
        pet.setHunger(Math.min(pet.getHunger() + 20, 100));
        return petRepository.save(pet);
    }

    public Pet playWithPet(Long petId) {
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new RuntimeException("Pet not found"));
        if (pet.getEnergy() > 20) {
            pet.setHappiness(Math.min(pet.getHappiness() + 15, 100));
            pet.setEnergy(pet.getEnergy() - 20);
        }
        return petRepository.save(pet);
    }

    public Pet getPetById(Long petId) {
        return petRepository.findById(petId).orElseThrow(() -> new RuntimeException("Pet not found"));
    }
    // Otros métodos: dormir, curar, etc.
}
