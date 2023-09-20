package com.cinema.cinemaproject.service;

import com.cinema.cinemaproject.entity.Movie;
import com.cinema.cinemaproject.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class MovieService{
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(int Id) {
        Optional<Movie> result = movieRepository.findById(Id);

        Movie movie = null;
         if(result.isPresent()) {
             movie = result.get();
         }
         else {
             throw new RuntimeException("Movie not found with ID: "+Id);
         }

         return movie;
    }

    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    public void deleteById(int Id) {
        movieRepository.deleteById(Id);
    }


    public Movie findMovieByTitle(String title) {
        Optional<Movie> result = movieRepository.findByTitle(title);

        Movie movie = null;

        if(result.isPresent()) {
            movie = result.get();
        }
        else {
            throw new NoSuchElementException("The movie with title: "+title+" was not found.");
        }

        return movie;
    }

    public List<Movie> findMoviesByMinDuration(int minDuration) {
        Optional<List<Movie>> result = movieRepository.findMoviesByMinDuration(minDuration);

        List<Movie> movie = null;

        if(result.isPresent()) {
            movie = result.get();
        }
        else {
            throw new NoSuchElementException("No movies found with minimum duration: "+ minDuration+" mins.");
        }

        return movie;
    }


    public List<Movie> findMoviesByMaxDuration(int maxDuration) {
        Optional<List<Movie>> result = movieRepository.findMoviesByMaxDuration(maxDuration);

        List<Movie> movie = null;

        if(result.isPresent()) {
            movie = result.get();
        }
        else {
            throw new NoSuchElementException("No movies found with maximum duration: "+ maxDuration+" mins.");
        }

        return movie;
    }

    public List<Movie> findMoviesByDuration(int duration) {
        Optional<List<Movie>> result = movieRepository.findMoviesByDuration(duration);

        List<Movie> movie = null;

        if(result.isPresent()) {
            movie = result.get();
        }
        else {
            throw new NoSuchElementException("No movies found with duration: "+ duration+" mins.");
        }

        return movie;
    }
}
