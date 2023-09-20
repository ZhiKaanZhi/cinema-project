package com.cinema.cinemaproject.repository;

import com.cinema.cinemaproject.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query("""
           SELECT * FROM movie
           where title = :title
           """)
    Optional<Movie> findByTitle(@Param("title") String title);

    @Query("""
            SELECT * FROM movie
            where durationInMin >= :minDuration
            """)
    Optional<List<Movie>> findMoviesByMinDuration(@Param("minDuration") int minDuration);

    @Query("""
            SELECT * FROM movie
            where durationInMin <= :minDuration
            """)
    Optional<List<Movie>> findMoviesByMaxDuration(@Param("maxDuration") int maxDuration);

    @Query("""
            SELECT * FROM movie
            where durationInMin = :duration
            """)
    Optional<List<Movie>> findMoviesByDuration(@Param("duration") int duration);
}
