package com.movies.moviesmanagement.repository;

import com.movies.moviesmanagement.entity.Director;
import com.movies.moviesmanagement.entity.enums.Country;
import com.movies.moviesmanagement.entity.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {

    // Find a director by name
    @Query(value = """
           SELECT * FROM Director
           WHERE directorName = :directorName
           """, nativeQuery = true)
    Director findDirectorByName(@Param("directorName") String directorName);

    // Find directors by gender
    @Query(value = """
           SELECT * FROM Director
           WHERE directorGender = :directorGender
           """, nativeQuery = true)
    List<Director> findDirectorsByGender(@Param("directorGender") Gender directorGender);

    // Find directors by date of birth
    @Query(value = """
           SELECT * FROM Director
           WHERE directorDateOfBirth = :directorDateOfBirth
           """, nativeQuery = true)
    List<Director> findDirectorsByDateOfBirth(@Param("directorDateOfBirth") Date directorDateOfBirth);

    // Find directors by nationality
    @Query(value = """
           SELECT * FROM Director
           WHERE directorNationality = :directorNationality
           """, nativeQuery = true)
    List<Director> findDirectorsByNationality(@Param("directorNationality") Country directorNationality);
}
