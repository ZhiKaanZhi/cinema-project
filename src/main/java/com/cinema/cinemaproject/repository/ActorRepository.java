package com.cinema.cinemaproject.repository;

import com.cinema.cinemaproject.entity.Actor;
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
public interface ActorRepository extends JpaRepository<Actor, Integer> {

    // Find an actor by name
    @Query(value = """
           SELECT * FROM Actor
           WHERE actorName = :actorName
           """, nativeQuery = true)
    Actor findActorByName(@Param("actorName") String actorName);

    // Find actors by gender
    @Query(value = """
           SELECT * FROM Actor
           WHERE actorGender = :actorGender
           """, nativeQuery = true)
    List<Actor> findActorByGender(@Param("actorGender") Gender actorGender);

    // Find actors by date of birth
    @Query(value = """
           SELECT * FROM Actor
           WHERE actorDateOfBirth = :actorDateOfBirth
           """, nativeQuery = true)
    List<Actor> findActorByDateOfBirth(@Param("actorDateOfBirth") Date actorDateOfBirth);

    // Find actors by nationality
    @Query(value = """
           SELECT * FROM Actor
           WHERE actorNationality = :actorNationality
           """, nativeQuery = true)
    List<Actor> findActorByNationality(@Param("actorNationality") Country actorNationality);
}
