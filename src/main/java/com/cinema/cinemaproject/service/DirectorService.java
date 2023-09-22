package com.cinema.cinemaproject.service;

import com.cinema.cinemaproject.ServiceContracts.dto.DirectorDTO;
import com.cinema.cinemaproject.entity.Director;
import com.cinema.cinemaproject.entity.enums.Country;
import com.cinema.cinemaproject.entity.enums.Gender;
import com.cinema.cinemaproject.repository.DirectorRepository;
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


    public void save(Director director) {
        directorRepository.save(director);
    }

    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    public DirectorDTO findById(int Id) {
        Optional<Director> result = directorRepository.findById(Id);

        DirectorDTO directorDTO = null;
        if(result.isPresent()) {
            directorDTO = Director.toDirectorDTO(result);
        }
        else {
            throw new RuntimeException("Director not found with ID: "+Id);
        }

        return directorDTO;
    }


    public void deleteById(int Id) {
        directorRepository.deleteById(Id);
    }

    public Optional<List<DirectorDTO>> findDirectorByName(String directorName) {
        Optional<List<Director>> result = directorRepository.findDirectorByName(directorName);

        List<DirectorDTO> directorDTOList = null;
        if(result.isPresent()) {
            for (Director director: result.get()) {
                directorDTOList.add(Director.toDirectorDTO(Optional.ofNullable(director)));
            }

        }
        else {
            throw new RuntimeException("Directors not found with name: "+directorName);
        }

        return Optional.ofNullable(directorDTOList);
    }

    public Optional<List<DirectorDTO>> findDirectorByGender(Gender directorGender) {
        Optional<List<Director>> result = directorRepository.findDirectorByGender(directorGender);

        List<DirectorDTO> directorDTOList = null;
        if(result.isPresent()) {
            for (Director director: result.get()) {
                directorDTOList.add(Director.toDirectorDTO(Optional.ofNullable(director)));
            }

        }
        else {
            throw new RuntimeException("Directors not found with gender: "+directorGender);
        }

        return Optional.ofNullable(directorDTOList);
    }

    public Optional<List<DirectorDTO>> findDirectorByDateOfBirth(Date directorDateOfBirth) {
        Optional<List<Director>> result = directorRepository.findDirectorByDateOfBirth(directorDateOfBirth);

        List<DirectorDTO> directorDTOList = null;
        if(result.isPresent()) {
            for (Director director: result.get()) {
                directorDTOList.add(Director.toDirectorDTO(Optional.ofNullable(director)));
            }

        }
        else {
            throw new RuntimeException("Directors not found with date of birth: "+directorDateOfBirth);
        }

        return Optional.ofNullable(directorDTOList);
    }

    public Optional<List<DirectorDTO>> findDirectorByNationality(Country directorNationality) {
        Optional<List<Director>> result = directorRepository.findDirectorByNationality(directorNationality);

        List<DirectorDTO> directorDTOList = null;
        if(result.isPresent()) {
            for (Director director: result.get()) {
                directorDTOList.add(Director.toDirectorDTO(Optional.ofNullable(director)));
            }

        }
        else {
            throw new RuntimeException("Directors not found with nationality: "+directorNationality);
        }

        return Optional.ofNullable(directorDTOList);
    }
}
