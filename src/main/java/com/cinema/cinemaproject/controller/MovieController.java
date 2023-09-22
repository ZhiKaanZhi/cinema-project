package com.cinema.cinemaproject.controller;

import com.cinema.cinemaproject.ServiceContracts.dto.MovieDTO;
import com.cinema.cinemaproject.entity.Movie;
import com.cinema.cinemaproject.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
