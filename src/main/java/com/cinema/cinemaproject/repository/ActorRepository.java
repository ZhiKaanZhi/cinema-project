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

    @Query(value = """
           SELECT * FROM Actor
           where actorName = :actorName
           """, nativeQuery = true)
    Optional<List<Actor>> findActorByName(@Param("actorName") String actorName);

    @Query(value = """
           SELECT * FROM Actor
           where actorGender = :actorGender
           """, nativeQuery = true)
    Optional<List<Actor>> findActorByGender(@Param("actorGender") Gender actorGender);

    @Query(value = """
           SELECT * FROM Actor
           where actorDateOfBirth = :actorDateOfBirth
           """, nativeQuery = true)
    Optional<List<Actor>> findActorByDateOfBirth(@Param("actorDateOfBirth") Date actorDateOfBirth);

    @Query(value = """
           SELECT * FROM Actor
           where actorNationality = :actorNationality
           """, nativeQuery = true)
    Optional<List<Actor>> findActorByNationality(@Param("actorNationality") Country actorNationality);
}
