package com.cinema.cinemaproject.service;

import com.cinema.cinemaproject.entity.Director;
import com.cinema.cinemaproject.mapstruct.dtos.ActorAllDto;
import com.cinema.cinemaproject.mapstruct.dtos.ActorDto;
import com.cinema.cinemaproject.entity.Actor;
import com.cinema.cinemaproject.entity.enums.Country;
import com.cinema.cinemaproject.entity.enums.Gender;
import com.cinema.cinemaproject.mapstruct.dtos.DirectorDto;
import com.cinema.cinemaproject.mapstruct.mappers.MapStructMapper;
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
    private final MapStructMapper mapstructMapper;

    public ActorService(ActorRepository actorRepository, MapStructMapper mapstructMapper) {
        this.actorRepository = actorRepository;
        this.mapstructMapper = mapstructMapper;
    }

    @Transactional
    public void save(ActorAllDto actor) {
        actorRepository.save(mapstructMapper.actorAllDtoToActor(actor));
    }

    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    public List<ActorDto> findAllActorDtos() {
        List<Actor> result = actorRepository.findAll();
        List<ActorDto> actorDtoList = new ArrayList<ActorDto>();

        if(!result.isEmpty()) {
            for(Actor actor: result){
                actorDtoList.add(mapstructMapper.actorToActorDto(actor));
            }
        }
        else {
            return null;
        }
        return actorDtoList;
    }

    public ActorDto findById(int Id) {
        return mapstructMapper.actorToActorDto(actorRepository.findById(Id).orElse(null));
    }

    @Transactional
    public void deleteById(int Id) {
        actorRepository.deleteById(Id);
    }

    public ActorDto findActorByName(String actorName) {
        Optional<Actor> result = actorRepository.findActorByName(actorName);

        return mapstructMapper.actorToActorDto(result.orElse(null));
    }

    public List<ActorDto> findActorByGender(Gender actorGender) {
        Optional<List<Actor>> result = actorRepository.findActorByGender(actorGender);

        List<ActorDto> actorDtoList = new ArrayList<ActorDto>();
        if(result.isPresent()) {
            for (Actor actor: result.get()) {
                actorDtoList.add(mapstructMapper.actorToActorDto(actor));
            }
        }
        else {
            return null;
            //throw new RuntimeException("Actors not found with gender: "+directorGender);
        }

        return actorDtoList;
    }

    public List<ActorDto> findActorByDateOfBirth(Date actorDateOfBirth) {
        Optional<List<Actor>> result = actorRepository.findActorByDateOfBirth(actorDateOfBirth);

        List<ActorDto> actorDtoList = new ArrayList<ActorDto>();
        if(result.isPresent()) {
            for (Actor actor: result.get()) {
                actorDtoList.add(mapstructMapper.actorToActorDto(actor));
            }
        }
        else {
            return null;
            //throw new RuntimeException("Actors not found with date of birth: "+actorDateOfBirth);
        }
        return actorDtoList;
    }

    public List<ActorDto> findActorByNationality(Country actorNationality) {
        Optional<List<Actor>> result = actorRepository.findActorByNationality(actorNationality);

        List<ActorDto> actorDtoList = new ArrayList<ActorDto>();
        if(result.isPresent()) {
            for (Actor actor: result.get()) {
                actorDtoList.add(mapstructMapper.actorToActorDto(actor));
            }

        }
        else {
            return null;
            //throw new RuntimeException("Actors not found with nationality: "+actorNationality);
        }

        return actorDtoList;
    }
}
