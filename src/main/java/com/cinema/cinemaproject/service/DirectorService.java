package com.cinema.cinemaproject.service;

import com.cinema.cinemaproject.mapstruct.dtos.DirectorAllDto;
import com.cinema.cinemaproject.entity.Director;
import com.cinema.cinemaproject.entity.enums.Country;
import com.cinema.cinemaproject.entity.enums.Gender;
import com.cinema.cinemaproject.mapstruct.dtos.DirectorDto;
import com.cinema.cinemaproject.mapstruct.mappers.DirectorMapper;
import com.cinema.cinemaproject.repository.DirectorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DirectorService {
    private final DirectorRepository directorRepository;
    private final DirectorMapper mapstructMapper;

    @Autowired
    public DirectorService(DirectorRepository directorRepository, DirectorMapper mapstructMapper) {
        this.directorRepository = directorRepository;
        this.mapstructMapper = mapstructMapper;
    }

    /**
     * Save a new director or update an existing one.
     *
     * @param directorAllDto The DirectorAllDto containing director details to save.
     * @return Saved DirectorDto.
     */
    @Transactional
    public DirectorDto save(DirectorAllDto directorAllDto) {
        // Save and flush the director entity, then map it to DTO
        Director savedDirector = directorRepository.saveAndFlush(mapstructMapper.directorAllDtoToDirector(directorAllDto));
        return mapstructMapper.directorToDirectorDto(savedDirector);
    }

    /**
     * Retrieve a list of all directors.
     *
     * @return List of Director entities.
     */
    public List<Director> findAll() {
        // Retrieve all directors
        return directorRepository.findAll();
    }

    /**
     * Retrieve a list of all directors as DirectorDto objects.
     *
     * @return List of DirectorDto objects.
     */
    public List<DirectorDto> findAllDirectorDtos() {
        // Retrieve all directors and map them to DTOs
        List<Director> result = directorRepository.findAll();
        List<DirectorDto> directorDtoList = new ArrayList<>();

        for (Director director : result) {
            directorDtoList.add(mapstructMapper.directorToDirectorDto(director));
        }

        return directorDtoList;
    }

    /**
     * Retrieve a director by its ID.
     *
     * @param id The ID of the director to retrieve.
     * @return DirectorDto if found, null if not found.
     */
    public DirectorDto findById(int id) {
        // Find director by ID and map to DTO
        return mapstructMapper.directorToDirectorDto(directorRepository.findById(id).orElse(null));
    }

    /**
     * Delete a director by its ID.
     *
     * @param id The ID of the director to delete.
     * @return Deleted DirectorDto if found and deleted, null if not found.
     */
    @Transactional
    public DirectorDto deleteById(int id) {
        // Find and delete director by ID, then map it to DTO
        Director directorToDelete = directorRepository.findById(id).orElse(null);
        if (directorToDelete != null) {
            directorRepository.deleteById(id);
        }
        return mapstructMapper.directorToDirectorDto(directorToDelete);
    }

    /**
     * Find a director by their name.
     *
     * @param directorName The name of the director to find.
     * @return DirectorDto if found, null if not found.
     */
    public DirectorDto findDirectorByName(String directorName) {
        // Find director by name and map to DTO
        Director director = directorRepository.findDirectorByName(directorName);
        return mapstructMapper.directorToDirectorDto(director);
    }

    /**
     * Find directors by gender.
     *
     * @param directorGender The gender of directors to find.
     * @return List of DirectorDto objects that meet the criteria.
     */
    public List<DirectorDto> findDirectorsByGender(Gender directorGender) {
        // Find directors by gender and map to DTOs
        List<Director> result = directorRepository.findDirectorsByGender(directorGender);
        List<DirectorDto> directorDtoList = new ArrayList<>();

        for (Director director : result) {
            directorDtoList.add(mapstructMapper.directorToDirectorDto(director));
        }

        return directorDtoList;
    }

    /**
     * Find directors by date of birth.
     *
     * @param directorDateOfBirth The date of birth of directors to find.
     * @return List of DirectorDto objects that meet the criteria.
     */
    public List<DirectorDto> findDirectorsByDateOfBirth(Date directorDateOfBirth) {
        // Find directors by date of birth and map to DTOs
        List<Director> result = directorRepository.findDirectorsByDateOfBirth(directorDateOfBirth);
        List<DirectorDto> directorDtoList = new ArrayList<>();

        for (Director director : result) {
            directorDtoList.add(mapstructMapper.directorToDirectorDto(director));
        }

        return directorDtoList;
    }

    /**
     * Find directors by nationality.
     *
     * @param directorNationality The nationality of directors to find.
     * @return List of DirectorDto objects that meet the criteria.
     */
    public List<DirectorDto> findDirectorsByNationality(Country directorNationality) {
        // Find directors by nationality and map to DTOs
        List<Director> result = directorRepository.findDirectorsByNationality(directorNationality);
        List<DirectorDto> directorDtoList = new ArrayList<>();

        for (Director director : result) {
            directorDtoList.add(mapstructMapper.directorToDirectorDto(director));
        }

        return directorDtoList;
    }
}
