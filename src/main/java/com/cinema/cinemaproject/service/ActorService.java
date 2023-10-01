package com.cinema.cinemaproject.service;

import com.cinema.cinemaproject.mapstruct.dtos.ActorAllDto;
import com.cinema.cinemaproject.mapstruct.dtos.ActorDto;
import com.cinema.cinemaproject.entity.Actor;
import com.cinema.cinemaproject.entity.enums.Country;
import com.cinema.cinemaproject.entity.enums.Gender;
import com.cinema.cinemaproject.mapstruct.mappers.ActorMapper;
import com.cinema.cinemaproject.repository.ActorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ActorService {
    private final ActorRepository actorRepository;
    private final ActorMapper mapstructMapper;

    // Constructor to inject dependencies
    public ActorService(ActorRepository actorRepository, ActorMapper mapstructMapper) {
        this.actorRepository = actorRepository;
        this.mapstructMapper = mapstructMapper;
    }

    /**
     * Save a new actor or update an existing one.
     *
     * @param actor The ActorAllDto containing actor details to save.
     * @return Saved ActorDto.
     */
    @Transactional
    public ActorDto save(ActorAllDto actor) {
        // Save the actor entity and flush changes to the database
        Actor savedActor = actorRepository.saveAndFlush(mapstructMapper.actorAllDtoToActor(actor));
        // Convert the saved actor entity to a DTO and return it
        return mapstructMapper.actorToActorDto(savedActor);
    }

    /**
     * Retrieve a list of all actors.
     *
     * @return List of all Actor entities.
     */
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    /**
     * Retrieve a list of all actors as ActorDto objects.
     *
     * @return List of ActorDto objects.
     */
    public List<ActorDto> findAllActorDtos() {
        // Retrieve all actor entities from the repository
        List<Actor> result = actorRepository.findAll();
        List<ActorDto> actorDtoList = new ArrayList<>();

        if (!result.isEmpty()) {
            // Convert each actor entity to a DTO and add it to the list
            for (Actor actor : result) {
                actorDtoList.add(mapstructMapper.actorToActorDto(actor));
            }
        } else {
            // Return null if no actors are found
            return null;
        }
        return actorDtoList;
    }

    /**
     * Retrieve an actor by its ID and return as an ActorDto.
     *
     * @param Id The ID of the actor to retrieve.
     * @return ActorDto if found, null if not found.
     */
    public ActorDto findById(int Id) {
        // Find the actor entity by ID and convert it to a DTO, or return null if not found
        return mapstructMapper.actorToActorDto(actorRepository.findById(Id).orElse(null));
    }

    /**
     * Delete an actor by its ID.
     *
     * @param Id The ID of the actor to delete.
     * @return Deleted ActorDto if found and deleted, null if not found.
     */
    @Transactional
    public ActorDto deleteById(int Id) {
        Actor actorToDelete = actorRepository.findById(Id).orElse(null);
        if (actorToDelete != null) {
            actorRepository.deleteById(Id);
        }
        return mapstructMapper.actorToActorDto(actorToDelete);
    }

    /**
     * Find an actor by name and return as an ActorDto.
     *
     * @param actorName The name of the actor to find.
     * @return ActorDto if found, null if not found.
     */
    public ActorDto findActorByName(String actorName) {
        Actor actor = actorRepository.findActorByName(actorName);
        return mapstructMapper.actorToActorDto(actor);
    }

    /**
     * Find actors by gender and return as a list of ActorDto objects.
     *
     * @param actorGender The gender of actors to find.
     * @return List of ActorDto objects.
     */
    public List<ActorDto> findActorByGender(Gender actorGender) {
        List<Actor> result = actorRepository.findActorByGender(actorGender);
        List<ActorDto> actorDtoList = new ArrayList<>();

        if (!result.isEmpty()) {
            // Convert each actor entity to a DTO and add it to the list
            for (Actor actor : result) {
                actorDtoList.add(mapstructMapper.actorToActorDto(actor));
            }
        } else {
            // Return null if no actors are found
            return null;
        }

        return actorDtoList;
    }

    /**
     * Find actors by date of birth and return as a list of ActorDto objects.
     *
     * @param actorDateOfBirth The date of birth of actors to find.
     * @return List of ActorDto objects.
     */
    public List<ActorDto> findActorByDateOfBirth(Date actorDateOfBirth) {
        List<Actor> result = actorRepository.findActorByDateOfBirth(actorDateOfBirth);
        List<ActorDto> actorDtoList = new ArrayList<>();

        if (!result.isEmpty()) {
            // Convert each actor entity to a DTO and add it to the list
            for (Actor actor : result) {
                actorDtoList.add(mapstructMapper.actorToActorDto(actor));
            }
        } else {
            // Return null if no actors are found
            return null;
        }

        return actorDtoList;
    }

    /**
     * Find actors by nationality and return as a list of ActorDto objects.
     *
     * @param actorNationality The nationality of actors to find.
     * @return List of ActorDto objects.
     */
    public List<ActorDto> findActorByNationality(Country actorNationality) {
        List<Actor> result = actorRepository.findActorByNationality(actorNationality);
        List<ActorDto> actorDtoList = new ArrayList<>();

        if (!result.isEmpty()) {
            // Convert each actor entity to a DTO and add it to the list
            for (Actor actor : result) {
                actorDtoList.add(mapstructMapper.actorToActorDto(actor));
            }
        } else {
            // Return null if no actors are found
            return null;
        }

        return actorDtoList;
    }
}
