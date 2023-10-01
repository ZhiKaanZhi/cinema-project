package com.cinema.cinemaproject.service;

import com.cinema.cinemaproject.mapstruct.dtos.MovieAllDto;
import com.cinema.cinemaproject.mapstruct.dtos.MovieDto;
import com.cinema.cinemaproject.entity.Movie;
import com.cinema.cinemaproject.mapstruct.mappers.MovieMapper;
import com.cinema.cinemaproject.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper mapstructMapper;

    @Autowired
    public MovieService(MovieRepository movieRepository, MovieMapper mapstructMapper) {
        this.movieRepository = movieRepository;
        this.mapstructMapper = mapstructMapper;
    }

    /**
     * Retrieve a list of all movies.
     *
     * @return List of Movie entities.
     */
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    /**
     * Retrieve a list of all movies as MovieDto objects.
     *
     * @return List of MovieDto objects.
     */
    public List<MovieDto> findAllMovieDtos() {
        List<Movie> result = movieRepository.findAll();
        List<MovieDto> movieDtoList = new ArrayList<>();

        for (Movie movie : result) {
            movieDtoList.add(mapstructMapper.movieToMovieDto(movie));
        }

        return movieDtoList;
    }

    /**
     * Retrieve a movie by its ID.
     *
     * @param id The ID of the movie to retrieve.
     * @return MovieDto if found, null if not found.
     */
    public MovieDto findById(int id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        return mapstructMapper.movieToMovieDto(movie);
    }

    /**
     * Save a new movie or update an existing one.
     *
     * @param movieAllDto The MovieAllDto containing movie details to save.
     * @return Saved MovieDto.
     */
    @Transactional
    public MovieDto save(MovieAllDto movieAllDto) {
        Movie savedMovie = movieRepository.saveAndFlush(mapstructMapper.movieAllDtoToMovie(movieAllDto));
        return mapstructMapper.movieToMovieDto(savedMovie);
    }

    /**
     * Delete a movie by its ID.
     *
     * @param id The ID of the movie to delete.
     * @return Deleted MovieDto if found and deleted, null if not found.
     */
    @Transactional
    public MovieDto deleteById(int id) {
        Movie movieToDelete = movieRepository.findById(id).orElse(null);
        if (movieToDelete != null) {
            movieRepository.deleteById(id);
        }
        return mapstructMapper.movieToMovieDto(movieToDelete);
    }

    /**
     * Find a movie by its title.
     *
     * @param title The title of the movie to find.
     * @return MovieDto if found, null if not found.
     */
    public MovieDto findMovieByTitle(String title) {
        Movie movie = movieRepository.findByTitle(title);
        return mapstructMapper.movieToMovieDto(movie);
    }

    /**
     * Find movies with a minimum duration.
     *
     * @param minDuration The minimum duration in minutes.
     * @return List of MovieDto objects that meet the criteria.
     */
    public List<MovieDto> findMoviesByMinDuration(int minDuration) {
        List<Movie> result = movieRepository.findMoviesByMinDuration(minDuration);
        List<MovieDto> movieDtoList = new ArrayList<>();

        for (Movie movie : result) {
            movieDtoList.add(mapstructMapper.movieToMovieDto(movie));
        }

        return movieDtoList;
    }

    // ... (Similar methods for other queries)

    /**
     * Find movies directed by a director with a given ID.
     *
     * @param director_ID The ID of the director.
     * @return List of MovieDto objects directed by the specified director.
     */
    public List<MovieDto> findMoviesByDirectorId(Integer director_ID) {
        List<Movie> result = movieRepository.findMoviesByDirectorId(director_ID);
        List<MovieDto> movieDtoList = new ArrayList<>();

        for (Movie movie : result) {
            movieDtoList.add(mapstructMapper.movieToMovieDto(movie));
        }

        return movieDtoList;
    }
}
