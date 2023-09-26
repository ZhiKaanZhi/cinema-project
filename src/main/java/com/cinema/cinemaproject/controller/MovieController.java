package com.cinema.cinemaproject.controller;

import com.cinema.cinemaproject.mapstruct.dtos.MovieAllDto;
import com.cinema.cinemaproject.mapstruct.dtos.MovieDto;
import com.cinema.cinemaproject.entity.Movie;
import com.cinema.cinemaproject.service.MovieService;
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
    public List<Movie> findAll() {
        return movieService.findAll();
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
}
