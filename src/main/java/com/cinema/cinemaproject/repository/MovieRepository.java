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

    // Find a movie by title
    @Query(value = "SELECT * FROM Movie WHERE movieTitle = :title", nativeQuery = true)
    Movie findByTitle(@Param("title") String title);

    // Find movies with duration greater than or equal to the given duration
    @Query(value = "SELECT * FROM Movie WHERE movieDurationInMin >= :minDuration", nativeQuery = true)
    List<Movie> findMoviesByMinDuration(@Param("minDuration") int minDuration);

    // Find movies with duration less than or equal to the given duration
    @Query(value = "SELECT * FROM Movie WHERE movieDurationInMin <= :maxDuration", nativeQuery = true)
    List<Movie> findMoviesByMaxDuration(@Param("maxDuration") int maxDuration);

    // Find movies with the exact duration
    @Query(value = "SELECT * FROM Movie WHERE movieDurationInMin = :duration", nativeQuery = true)
    List<Movie> findMoviesByDuration(@Param("duration") int duration);

    // Find movies with ticket price greater than or equal to the given price
    @Query(value = "SELECT * FROM Movie WHERE moviePrice >= :price", nativeQuery = true)
    List<Movie> findMoviesByMinPrice(@Param("price") long minPrice);

    // Find movies with ticket price less than or equal to the given price
    @Query(value = "SELECT * FROM Movie WHERE moviePrice <= :maxPrice", nativeQuery = true)
    List<Movie> findMoviesByMaxPrice(@Param("maxPrice") long maxPrice);

    // Find movies with the exact ticket price
    @Query(value = "SELECT * FROM Movie WHERE moviePrice = :price", nativeQuery = true)
    List<Movie> findMoviesByPrice(@Param("price") long price);

    // Find movies by release date
    @Query(value = "SELECT * FROM Movie WHERE movieReleaseDate = :movieReleaseDate", nativeQuery = true)
    List<Movie> findMoviesByReleaseDate(@Param("movieReleaseDate") Date movieReleaseDate);

    // Find movies directed by a director with the given ID
    @Query(value = "SELECT * FROM Movie WHERE director_ID = :director_ID", nativeQuery = true)
    List<Movie> findMoviesByDirectorId(@Param("director_ID") Integer director_ID);
}
