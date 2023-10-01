package com.cinema.cinemaproject.service;

import com.cinema.cinemaproject.entity.Actor;
import com.cinema.cinemaproject.entity.Director;
import com.cinema.cinemaproject.entity.Movie;
import com.cinema.cinemaproject.mapstruct.dtos.MovieAllDto;
import com.cinema.cinemaproject.mapstruct.mappers.MovieMapper;
import com.cinema.cinemaproject.repository.ActorRepository;
import com.cinema.cinemaproject.repository.DirectorRepository;
import com.cinema.cinemaproject.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieDirectorActorService {

    @Autowired
    private final MovieMapper movieMapper;

    @Autowired
    private final MovieRepository movieRepository;

    @Autowired
    private final DirectorRepository directorRepository;

    @Autowired
    private final ActorRepository actorRepository;

    public MovieDirectorActorService(MovieMapper movieMapper, MovieRepository movieRepository, DirectorRepository directorRepository, ActorRepository actorRepository) {
        this.movieMapper = movieMapper;
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
        this.actorRepository = actorRepository;
    }


    @Transactional
    public MovieAllDto saveMovieWithAssociations(MovieAllDto movieAllDto) {
        // Convert DTO to entity
        Movie movieEntity = movieMapper.movieAllDtoToMovie(movieAllDto);

        // Set or create the director and actors on the entity
        setOrCreateDirectorForMovie(movieEntity, movieAllDto.getMovieDirectorId());
        setOrCreateActorsForMovie(movieEntity, movieAllDto.getMovieActorIds());

        // Save the movie entity
        movieEntity = movieRepository.save(movieEntity);

        // Return the saved entity as a DTO
        return movieMapper.movieToMovieAllDto(movieEntity);
    }

    private void setOrCreateDirectorForMovie(Movie movie, Integer directorId) {
        System.out.println("Saving director with ID: "+ directorId+ " in Movie: "+movie);
        Director director = directorRepository.findById(directorId)
                .orElseGet(() -> {
                    Director newDirector = new Director();
                    newDirector.setDirectorID(directorId); // Assuming you want to manually set the ID
                    return directorRepository.save(newDirector);
                });

        movie.setMovieDirector(director);
    }

    private void setOrCreateActorsForMovie(Movie movie, Set<Integer> actorIds) {
        System.out.println("Saving actors with IDs: "+ actorIds+ " in Movie: "+movie);
        Set<Actor> actors = actorIds.stream()
                .map(id -> actorRepository.findById(id).orElseGet(() -> {
                    Actor newActor = new Actor();
                    newActor.setActorID(id);  // Assuming you want to manually set the ID
                    return actorRepository.save(newActor);
                }))
                .collect(Collectors.toSet());

        movie.setMovieActors(actors);

        // Update the relationship on the actor side
        for (Actor actor : actors) {
            actor.addMovie(movie); // Use the synchronization helper method
        }
    }
}
