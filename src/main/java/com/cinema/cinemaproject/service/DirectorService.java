package com.cinema.cinemaproject.service;

import com.cinema.cinemaproject.mapstruct.dtos.DirectorAllDto;
import com.cinema.cinemaproject.entity.Director;
import com.cinema.cinemaproject.entity.enums.Country;
import com.cinema.cinemaproject.entity.enums.Gender;
import com.cinema.cinemaproject.mapstruct.dtos.DirectorDto;
import com.cinema.cinemaproject.mapstruct.mappers.MapStructMapper;
import com.cinema.cinemaproject.repository.DirectorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DirectorService {
    private final DirectorRepository directorRepository;
    private final MapStructMapper mapstructMapper;

    @Autowired
    public DirectorService(DirectorRepository directorRepository, MapStructMapper mapstructMapper) {
        this.directorRepository = directorRepository;
        this.mapstructMapper = mapstructMapper;
    }


    @Transactional
    public void save(DirectorAllDto directorAllDto) {
        directorRepository.save(mapstructMapper.directorAllDtoToDirector(directorAllDto));
    }

    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    public List<DirectorDto> findAllDiretctorDtos() {
        List<Director> result = directorRepository.findAll();
        List<DirectorDto> directorDtoList = new ArrayList<DirectorDto>();
        if(!result.isEmpty()) {
            for(Director director: result){
                directorDtoList.add(mapstructMapper.directorToDirectorDto(director));
            }
        }
        else {
            return null;
        }
        return directorDtoList;
    }

    public DirectorDto findById(int Id) {
        return mapstructMapper.directorToDirectorDto(directorRepository.findById(Id).orElse(null));
    }


    @Transactional
    public void deleteById(int Id) {
        directorRepository.deleteById(Id);
    }

    public DirectorDto findDirectorByName(String directorName) {
        Optional<Director> result = directorRepository.findDirectorByName(directorName);

        return mapstructMapper.directorToDirectorDto(result.orElse(null));
    }

    public List<DirectorDto> findDirectorByGender(Gender directorGender) {
        Optional<List<Director>> result = directorRepository.findDirectorByGender(directorGender);

        List<DirectorDto> directorDtoList = new ArrayList<DirectorDto>();
        if(result.isPresent()) {
            for (Director director: result.get()) {
                directorDtoList.add(mapstructMapper.directorToDirectorDto(director));
            }
        }
        else {
            return null;
            //throw new RuntimeException("Directors not found with gender: "+directorGender);
        }

        return directorDtoList;
    }

    public List<DirectorDto> findDirectorByDateOfBirth(Date directorDateOfBirth) {
        Optional<List<Director>> result = directorRepository.findDirectorByDateOfBirth(directorDateOfBirth);

        List<DirectorDto> directorDtoList = new ArrayList<DirectorDto>();
        if(result.isPresent()) {
            for (Director director: result.get()) {
                directorDtoList.add(mapstructMapper.directorToDirectorDto(director));
            }

        }
        else {
            return null;
            //throw new RuntimeException("Directors not found with date of birth: "+directorDateOfBirth);
        }

        return directorDtoList;
    }

    public List<DirectorDto> findDirectorByNationality(Country directorNationality) {
        Optional<List<Director>> result = directorRepository.findDirectorByNationality(directorNationality);

        List<DirectorDto> directorDtoList = new ArrayList<DirectorDto>();
        if(result.isPresent()) {
            for (Director director: result.get()) {
                directorDtoList.add(mapstructMapper.directorToDirectorDto(director));
            }

        }
        else {
            return null;
            //throw new RuntimeException("Directors not found with nationality: "+directorNationality);
        }

        return directorDtoList;
    }
}
