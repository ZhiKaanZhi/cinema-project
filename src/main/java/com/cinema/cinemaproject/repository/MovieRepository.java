package com.cinema.cinemaproject.repository;

import com.cinema.cinemaproject.mapstruct.dtos.MovieDto;
import com.cinema.cinemaproject.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query(value = """
           SELECT * FROM Movie
           where movieTitle = :title
           """, nativeQuery = true)
    Optional<Movie> findByTitle(@Param("title") String title);

    @Query(value = """
            SELECT * FROM Movie
            where movieDurationInMin >= :minDuration
            """, nativeQuery = true)
    Optional<List<Movie>> findMoviesByMinDuration(@Param("minDuration") int minDuration);

    @Query(value = """
            SELECT * FROM Movie
            where movieDurationInMin <= :maxDuration
            """, nativeQuery = true)
    Optional<List<Movie>> findMoviesByMaxDuration(@Param("maxDuration") int maxDuration);

    @Query(value = """
            SELECT * FROM Movie
            where movieDurationInMin = :duration
            """, nativeQuery = true)
    Optional<List<Movie>> findMoviesByDuration(@Param("duration") int duration);


    @Query(value = """
            SELECT * FROM Movie
            where moviePrice >= :price
            """, nativeQuery = true)
    Optional<List<Movie>> findMoviesByMinPrice(@Param("price") long minPrice);

    @Query(value = """
            SELECT * FROM Movie
            where moviePrice <= :maxPrice
            """, nativeQuery = true)
    Optional<List<Movie>> findMoviesByMaxPrice(@Param("maxPrice") long maxPrice);

    @Query(value = """
            SELECT * FROM Movie
            where moviePrice = :price
            """, nativeQuery = true)
    Optional<List<Movie>> findMoviesByPrice(@Param("price") long price);

    @Query(value = """
            SELECT * FROM Movie
            where movieReleaseDate = :movieReleaseDate
            """, nativeQuery = true)
    Optional<List<Movie>> findMoviesByReleaseDate(@Param("movieReleaseDate") Date movieReleaseDate);


    @Query(value = """
           SELECT * FROM Movie
           where director_ID = :director_ID
           """, nativeQuery = true)
    Optional<List<Movie>> findMoviesByDirectorId(@Param("director_ID") Integer director_ID);
}
