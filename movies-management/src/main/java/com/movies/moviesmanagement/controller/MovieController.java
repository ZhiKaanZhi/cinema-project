package com.movies.moviesmanagement.controller;

import com.movies.moviesmanagement.mapstruct.dtos.MovieAllDto;
import com.movies.moviesmanagement.mapstruct.dtos.MovieDto;
import com.movies.moviesmanagement.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;


    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping("")
    // make a request and final all the pieces of content in the system
    public List<MovieDto> findAll() {
        return movieService.findAllMovieDtos();
    }

    @GetMapping("/{id}")
    // make a request and final all the pieces of content in the system
    public MovieDto findById(@PathVariable int id) {
        MovieDto tempMovie = movieService.findById(id);
        if(tempMovie == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found with id: "+ id);
        }
        return tempMovie;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void addMovie(@RequestBody MovieAllDto movie){
        movieService.save(movie);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable int id){
        movieService.deleteById(id);
    }


    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{title}")
    public MovieDto findMovieById(@PathVariable String title){
        return movieService.findMovieByTitle(title);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/by-director/{id}")
    public List<MovieDto> findMoviesByDirectorId(@PathVariable Integer id) {
        List<MovieDto> tempMovies = movieService.findMoviesByDirectorId(id);
        if(tempMovies.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No movies directed from director with ID: "+ id);
        }
        else{
            return tempMovies;
        }
    }
}
