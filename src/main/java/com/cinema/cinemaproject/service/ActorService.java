package com.cinema.cinemaproject.service;

import com.cinema.cinemaproject.mapstruct.dtos.ActorDto;
import com.cinema.cinemaproject.entity.Actor;
import com.cinema.cinemaproject.entity.enums.Country;
import com.cinema.cinemaproject.entity.enums.Gender;
import com.cinema.cinemaproject.repository.ActorRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public void save(Actor actor) {
        actorRepository.save(actor);
    }

    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    public ActorDto findById(int Id) {
        Optional<Actor> result = actorRepository.findById(Id);

        ActorDto actorDTO = null;
        if(result.isPresent()) {
            actorDTO = Actor.toActorDTO(result);
        }
        else {
            throw new RuntimeException("Actor not found with ID: "+Id);
        }

        return actorDTO;
    }

    @Transactional
    public void deleteById(int Id) {
        actorRepository.deleteById(Id);
    }

    public Optional<List<ActorDto>> findActorByName(String actorName) {
        Optional<List<Actor>> result = actorRepository.findActorByName(actorName);

        List<ActorDto> actorDtoList = null;
        if(result.isPresent()) {
            for (Actor actor: result.get()) {
                actorDtoList.add(Actor.toActorDTO(Optional.ofNullable(actor)));
            }

        }
        else {
            throw new RuntimeException("Actors not found with name: "+actorName);
        }

        return Optional.ofNullable(actorDtoList);
    }

    public Optional<List<ActorDto>> findActorByGender(Gender actorGender) {
        Optional<List<Actor>> result = actorRepository.findActorByGender(actorGender);

        List<ActorDto> actorDtoList = null;
        if(result.isPresent()) {
            for (Actor actor: result.get()) {
                actorDtoList.add(Actor.toActorDTO(Optional.ofNullable(actor)));
            }

        }
        else {
            throw new RuntimeException("Actors not found with gender: "+actorGender);
        }

        return Optional.ofNullable(actorDtoList);
    }

    public Optional<List<ActorDto>> findActorByDateOfBirth(Date actorDateOfBirth) {
        Optional<List<Actor>> result = actorRepository.findActorByDateOfBirth(actorDateOfBirth);

        List<ActorDto> actorDtoList = null;
        if(result.isPresent()) {
            for (Actor actor: result.get()) {
                actorDtoList.add(Actor.toActorDTO(Optional.ofNullable(actor)));
            }

        }
        else {
            throw new RuntimeException("Actors not found with date of birth: "+actorDateOfBirth);
        }

        return Optional.ofNullable(actorDtoList);
    }

    public Optional<List<ActorDto>> findActorByNationality(Country actorNationality) {
        Optional<List<Actor>> result = actorRepository.findActorByNationality(actorNationality);

        List<ActorDto> actorDtoList = null;
        if(result.isPresent()) {
            for (Actor actor: result.get()) {
                actorDtoList.add(Actor.toActorDTO(Optional.ofNullable(actor)));
            }

        }
        else {
            throw new RuntimeException("Actors not found with nationality: "+actorNationality);
        }

        return Optional.ofNullable(actorDtoList);
    }
}
