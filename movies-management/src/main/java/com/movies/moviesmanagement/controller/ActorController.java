package com.movies.moviesmanagement.controller;

import com.movies.moviesmanagement.entity.Actor;
import com.movies.moviesmanagement.service.ActorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {
    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("")
    // make a request and final all the pieces of content in the system
    public List<Actor> findAll() {
        return actorService.findAll();
    }
}
