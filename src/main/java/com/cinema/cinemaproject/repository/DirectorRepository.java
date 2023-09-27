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

    @Query(value = """
           SELECT * FROM Director
           where directorName = :directorName
           """, nativeQuery = true)
    Optional<Director> findDirectorByName(@Param("directorName") String directorName);

    @Query(value = """
           SELECT * FROM Director
           where directorGender = :directorGender
           """, nativeQuery = true)
    Optional<List<Director>> findDirectorByGender(@Param("directorGender") Gender directorGender);

    @Query(value = """
           SELECT * FROM Director
           where directorDateOfBirth = :directorDateOfBirth
           """, nativeQuery = true)
    Optional<List<Director>> findDirectorByDateOfBirth(@Param("directorDateOfBirth") Date directorDateOfBirth);

    @Query(value = """
           SELECT * FROM Director
           where directorNationality = :directorNationality
           """, nativeQuery = true)
    Optional<List<Director>> findDirectorByNationality(@Param("directorNationality") Country directorNationality);


}
