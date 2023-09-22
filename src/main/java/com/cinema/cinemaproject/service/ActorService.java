package com.cinema.cinemaproject.service;

import com.cinema.cinemaproject.ServiceContracts.dto.ActorDTO;
import com.cinema.cinemaproject.entity.Actor;
import com.cinema.cinemaproject.entity.enums.Country;
import com.cinema.cinemaproject.entity.enums.Gender;
import com.cinema.cinemaproject.repository.ActorRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ActorService {
    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public void save(Actor actor) {
        actorRepository.save(actor);
    }

    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    public ActorDTO findById(int Id) {
        Optional<Actor> result = actorRepository.findById(Id);

        ActorDTO actorDTO = null;
        if(result.isPresent()) {
            actorDTO = Actor.toActorDTO(result);
        }
        else {
            throw new RuntimeException("Actor not found with ID: "+Id);
        }

        return actorDTO;
    }


    public void deleteById(int Id) {
        actorRepository.deleteById(Id);
    }

    public Optional<List<ActorDTO>> findActorByName(String actorName) {
        Optional<List<Actor>> result = actorRepository.findActorByName(actorName);

        List<ActorDTO> actorDTOList = null;
        if(result.isPresent()) {
            for (Actor actor: result.get()) {
                actorDTOList.add(Actor.toActorDTO(Optional.ofNullable(actor)));
            }

        }
        else {
            throw new RuntimeException("Actors not found with name: "+actorName);
        }

        return Optional.ofNullable(actorDTOList);
    }

    public Optional<List<ActorDTO>> findActorByGender(Gender actorGender) {
        Optional<List<Actor>> result = actorRepository.findActorByGender(actorGender);

        List<ActorDTO> actorDTOList = null;
        if(result.isPresent()) {
            for (Actor actor: result.get()) {
                actorDTOList.add(Actor.toActorDTO(Optional.ofNullable(actor)));
            }

        }
        else {
            throw new RuntimeException("Actors not found with gender: "+actorGender);
        }

        return Optional.ofNullable(actorDTOList);
    }

    public Optional<List<ActorDTO>> findActorByDateOfBirth(Date actorDateOfBirth) {
        Optional<List<Actor>> result = actorRepository.findActorByDateOfBirth(actorDateOfBirth);

        List<ActorDTO> actorDTOList = null;
        if(result.isPresent()) {
            for (Actor actor: result.get()) {
                actorDTOList.add(Actor.toActorDTO(Optional.ofNullable(actor)));
            }

        }
        else {
            throw new RuntimeException("Actors not found with date of birth: "+actorDateOfBirth);
        }

        return Optional.ofNullable(actorDTOList);
    }

    public Optional<List<ActorDTO>> findActorByNationality(Country actorNationality) {
        Optional<List<Actor>> result = actorRepository.findActorByNationality(actorNationality);

        List<ActorDTO> actorDTOList = null;
        if(result.isPresent()) {
            for (Actor actor: result.get()) {
                actorDTOList.add(Actor.toActorDTO(Optional.ofNullable(actor)));
            }

        }
        else {
            throw new RuntimeException("Actors not found with nationality: "+actorNationality);
        }

        return Optional.ofNullable(actorDTOList);
    }
}
