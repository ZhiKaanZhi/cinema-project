package com.cinema.cinemaproject.controller;

import com.cinema.cinemaproject.entity.Director;
import com.cinema.cinemaproject.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/directors")
public class DirectorController {

    private final DirectorService directorService;

    @Autowired
    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping("")
    // make a request and final all the pieces of content in the system
    public List<Director> findAll() {
        return directorService.findAll();
    }
}
