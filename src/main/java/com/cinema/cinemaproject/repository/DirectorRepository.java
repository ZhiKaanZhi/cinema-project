package com.cinema.cinemaproject.repository;

import com.cinema.cinemaproject.entity.Director;
import com.cinema.cinemaproject.entity.enums.Country;
import com.cinema.cinemaproject.entity.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {

    @Query("""
           SELECT * FROM director
           where directorName = :directorName
           """)
    Optional<List<Director>> findDirectorByName(@Param("directorName") String directorName);

    @Query("""
           SELECT * FROM director
           where directorGender = :directorGender
           """)
    Optional<List<Director>> findDirectorByGender(@Param("directorGender") Gender directorGender);

    @Query("""
           SELECT * FROM director
           where directorDateOfBirth = :directorDateOfBirth
           """)
    Optional<List<Director>> findDirectorByDateOfBirth(@Param("directorDateOfBirth") Date directorDateOfBirth);

    @Query("""
           SELECT * FROM director
           where directorNationality = :directorNationality
           """)
    Optional<List<Director>> findDirectorByNationality(@Param("directorNationality") Country directorNationality);


}
