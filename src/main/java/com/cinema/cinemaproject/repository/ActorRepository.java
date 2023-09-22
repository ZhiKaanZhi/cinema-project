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

    @Query("""
           SELECT * FROM actor
           where actorName = :actorName
           """)
    Optional<List<Actor>> findActorByName(@Param("actorName") String actorName);

    @Query("""
           SELECT * FROM actor
           where actorGender = :actorGender
           """)
    Optional<List<Actor>> findActorByGender(@Param("actorGender") Gender actorGender);

    @Query("""
           SELECT * FROM actor
           where actorDateOfBirth = :actorDateOfBirth
           """)
    Optional<List<Actor>> findActorByDateOfBirth(@Param("actorDateOfBirth") Date actorDateOfBirth);

    @Query("""
           SELECT * FROM director
           where actorNationality = :actorNationality
           """)
    Optional<List<Actor>> findActorByNationality(@Param("actorNationality") Country actorNationality);
}
