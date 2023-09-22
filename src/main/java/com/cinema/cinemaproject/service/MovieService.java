package com.cinema.cinemaproject.service;

import com.cinema.cinemaproject.ServiceContracts.dto.MovieDTO;
import com.cinema.cinemaproject.entity.Movie;
import com.cinema.cinemaproject.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MovieService{
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public MovieDTO findById(int Id) {
        Optional<Movie> result = movieRepository.findById(Id);

        MovieDTO movie = null;
         if(result.isPresent()) {
             movie = Movie.toMovieDTO(result);
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


    public MovieDTO findMovieByTitle(String title) {
        Optional<MovieDTO> result = movieRepository.findByTitle(title);

        MovieDTO movie = null;

        if(result.isPresent()) {
            movie = result.get();
        }
        else {
            throw new NoSuchElementException("The movie with title: "+title+" was not found.");
        }

        return movie;
    }

    public List<MovieDTO> findMoviesByMinDuration(int minDuration) {
        Optional<List<MovieDTO>> result = movieRepository.findMoviesByMinDuration(minDuration);

        List<MovieDTO> movie = null;

        if(result.isPresent()) {
            movie = result.get();
        }
        else {
            throw new NoSuchElementException("No movies found with minimum duration: "+ minDuration+" mins.");
        }

        return movie;
    }


    public List<MovieDTO> findMoviesByMaxDuration(int maxDuration) {
        Optional<List<MovieDTO>> result = movieRepository.findMoviesByMaxDuration(maxDuration);

        List<MovieDTO> movie = null;

        if(result.isPresent()) {
            movie = result.get();
        }
        else {
            throw new NoSuchElementException("No movies found with maximum duration: "+ maxDuration+" mins.");
        }

        return movie;
    }

    public List<MovieDTO> findMoviesByDuration(int duration) {
        Optional<List<MovieDTO>> result = movieRepository.findMoviesByDuration(duration);

        List<MovieDTO> movie = null;

        if(result.isPresent()) {
            movie = result.get();
        }
        else {
            throw new NoSuchElementException("No movies found with duration: "+ duration+" mins.");
        }

        return movie;
    }

    public List<MovieDTO> findMoviesByMinPrice(long minPrice) {
        Optional<List<MovieDTO>> result = movieRepository.findMoviesByMinPrice(minPrice);

        List<MovieDTO> movie = null;

        if(result.isPresent()) {
            movie = result.get();
        }
        else {
            throw new NoSuchElementException("No movies found with duration: "+ minPrice+" mins.");
        }

        return movie;
    }
}
