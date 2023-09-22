package com.cinema.cinemaproject.repository;

import com.cinema.cinemaproject.ServiceContracts.dto.MovieDTO;
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
           where movieTitle = :title
           """)
    Optional<MovieDTO> findByTitle(@Param("title") String title);

    @Query("""
            SELECT * FROM movie
            where movieDurationInMin >= :minDuration
            """)
    Optional<List<Movie>> findMoviesByMinDuration(@Param("minDuration") int minDuration);

    @Query("""
            SELECT * FROM movie
            where movieDurationInMin <= :minDuration
            """)
    Optional<List<Movie>> findMoviesByMaxDuration(@Param("maxDuration") int maxDuration);

    @Query("""
            SELECT * FROM movie
            where movieDurationInMin = :duration
            """)
    Optional<List<Movie>> findMoviesByDuration(@Param("duration") int duration);


    @Query("""
            SELECT * FROM movie
            where moviePrice >= :price
            """)
    Optional<List<Movie>> findMoviesByMinPrice(@Param("price") long minPrice);

    @Query("""
            SELECT * FROM movie
            where moviePrice <= :maxPrice
            """)
    Optional<List<Movie>> findMoviesByMaxPrice(@Param("maxPrice") long maxPrice);

    @Query("""
            SELECT * FROM movie
            where moviePrice = :price
            """)
    Optional<List<Movie>> findMoviesByPrice(@Param("price") long price);
}
