package com.cinema.cinemaproject.service;

import com.cinema.cinemaproject.ServiceContracts.dto.MovieDTO;
import com.cinema.cinemaproject.entity.Movie;
import com.cinema.cinemaproject.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MovieService{
    private final MovieRepository movieRepository;

    /**
     * Dependeny Injection of the movieRepository
     * @param movieRepository
     */
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
        Optional<List<Movie>> result = movieRepository.findMoviesByMinDuration(minDuration);

        List<MovieDTO> movieDTOList = null;

        if(result.isPresent()) {
            for (Movie movie: result.get()) {
                movieDTOList.add(Movie.toMovieDTO(Optional.ofNullable(movie)));
            }
        }
        else {
            throw new NoSuchElementException("No movies found with minimum duration: "+ minDuration+" mins.");
        }

        return movieDTOList;
    }


    public List<MovieDTO> findMoviesByMaxDuration(int maxDuration) {
        Optional<List<Movie>> result = movieRepository.findMoviesByMaxDuration(maxDuration);

        List<MovieDTO> movieDTOList = null;

        if(result.isPresent()) {
            for (Movie movie: result.get()) {
                movieDTOList.add(Movie.toMovieDTO(Optional.ofNullable(movie)));
            }
        }
        else {
            throw new NoSuchElementException("No movies found with minimum duration: "+ maxDuration+" mins.");
        }

        return movieDTOList;
    }

    public List<MovieDTO> findMoviesByDuration(int duration) {
        Optional<List<Movie>> result = movieRepository.findMoviesByDuration(duration);

        List<MovieDTO> movieDTOList = null;

        if(result.isPresent()) {
            for (Movie movie: result.get()) {
                movieDTOList.add(Movie.toMovieDTO(Optional.ofNullable(movie)));
            }
        }
        else {
            throw new NoSuchElementException("No movies found with minimum duration: "+ duration+" mins.");
        }

        return movieDTOList;
    }

    public List<MovieDTO> findMoviesByMinPrice(long minPrice) {
        Optional<List<Movie>> result = movieRepository.findMoviesByMinPrice(minPrice);

        List<MovieDTO> movieDTOList = null;

        if(result.isPresent()) {
            for (Movie movie: result.get()) {
                movieDTOList.add(Movie.toMovieDTO(Optional.ofNullable(movie)));
            }
        }
        else {
            throw new NoSuchElementException("No movies found with minimum price: "+ minPrice+" euros.");
        }

        return movieDTOList;
    }

    public List<MovieDTO> findMoviesByMaxPrice(long maxPrice) {
        Optional<List<Movie>> result = movieRepository.findMoviesByMaxPrice(maxPrice);

        List<MovieDTO> movieDTOList = null;

        if(result.isPresent()) {
            for (Movie movie: result.get()) {
                movieDTOList.add(Movie.toMovieDTO(Optional.ofNullable(movie)));
            }
        }
        else {
            throw new NoSuchElementException("No movies found with maximum price: "+ maxPrice+" euros.");
        }

        return movieDTOList;
    }

    public List<MovieDTO> findMoviesByPrice(long price) {
        Optional<List<Movie>> result = movieRepository.findMoviesByMaxPrice(price);

        List<MovieDTO> movieDTOList = null;

        if(result.isPresent()) {
            for (Movie movie: result.get()) {
                movieDTOList.add(Movie.toMovieDTO(Optional.ofNullable(movie)));
            }
        }
        else {
            throw new NoSuchElementException("No movies found with price: "+ price+" euros.");
        }

        return movieDTOList;
    }

    public List<MovieDTO> findMoviesByReleaseDate(Date movieReleaseDate) {
        Optional<List<Movie>> result = movieRepository.findMoviesByReleaseDate(movieReleaseDate);

        List<MovieDTO> movieDTOList = null;

        if(result.isPresent()) {
            for (Movie movie: result.get()) {
                movieDTOList.add(Movie.toMovieDTO(Optional.ofNullable(movie)));
            }
        }
        else {
            throw new NoSuchElementException("No movies found with release date: "+ movieReleaseDate);
        }

        return movieDTOList;
    }
}
