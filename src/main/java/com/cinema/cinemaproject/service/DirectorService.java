package com.cinema.cinemaproject.service;

import com.cinema.cinemaproject.mapstruct.dtos.DirectorAllDto;
import com.cinema.cinemaproject.entity.Director;
import com.cinema.cinemaproject.entity.enums.Country;
import com.cinema.cinemaproject.entity.enums.Gender;
import com.cinema.cinemaproject.repository.DirectorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DirectorService {
    private final DirectorRepository directorRepository;

    @Autowired
    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }


    @Transactional
    public void save(Director director) {
        directorRepository.save(director);
    }

    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    public DirectorAllDto findById(int Id) {
        Optional<Director> result = directorRepository.findById(Id);

        DirectorAllDto directorAllDTO = null;
        if(result.isPresent()) {
            directorAllDTO = Director.toDirectorDTO(result);
        }
        else {
            throw new RuntimeException("Director not found with ID: "+Id);
        }

        return directorAllDTO;
    }


    @Transactional
    public void deleteById(int Id) {
        directorRepository.deleteById(Id);
    }

    public Optional<List<DirectorAllDto>> findDirectorByName(String directorName) {
        Optional<List<Director>> result = directorRepository.findDirectorByName(directorName);

        List<DirectorAllDto> directorAllDtoList = null;
        if(result.isPresent()) {
            for (Director director: result.get()) {
                directorAllDtoList.add(Director.toDirectorDTO(Optional.ofNullable(director)));
            }

        }
        else {
            throw new RuntimeException("Directors not found with name: "+directorName);
        }

        return Optional.ofNullable(directorAllDtoList);
    }

    public Optional<List<DirectorAllDto>> findDirectorByGender(Gender directorGender) {
        Optional<List<Director>> result = directorRepository.findDirectorByGender(directorGender);

        List<DirectorAllDto> directorAllDtoList = null;
        if(result.isPresent()) {
            for (Director director: result.get()) {
                directorAllDtoList.add(Director.toDirectorDTO(Optional.ofNullable(director)));
            }

        }
        else {
            throw new RuntimeException("Directors not found with gender: "+directorGender);
        }

        return Optional.ofNullable(directorAllDtoList);
    }

    public Optional<List<DirectorAllDto>> findDirectorByDateOfBirth(Date directorDateOfBirth) {
        Optional<List<Director>> result = directorRepository.findDirectorByDateOfBirth(directorDateOfBirth);

        List<DirectorAllDto> directorAllDtoList = null;
        if(result.isPresent()) {
            for (Director director: result.get()) {
                directorAllDtoList.add(Director.toDirectorDTO(Optional.ofNullable(director)));
            }

        }
        else {
            throw new RuntimeException("Directors not found with date of birth: "+directorDateOfBirth);
        }

        return Optional.ofNullable(directorAllDtoList);
    }

    public Optional<List<DirectorAllDto>> findDirectorByNationality(Country directorNationality) {
        Optional<List<Director>> result = directorRepository.findDirectorByNationality(directorNationality);

        List<DirectorAllDto> directorAllDtoList = null;
        if(result.isPresent()) {
            for (Director director: result.get()) {
                directorAllDtoList.add(Director.toDirectorDTO(Optional.ofNullable(director)));
            }

        }
        else {
            throw new RuntimeException("Directors not found with nationality: "+directorNationality);
        }

        return Optional.ofNullable(directorAllDtoList);
    }
}
