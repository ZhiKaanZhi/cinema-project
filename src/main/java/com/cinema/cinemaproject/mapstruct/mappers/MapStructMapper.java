package com.cinema.cinemaproject.mapstruct.mappers;

import com.cinema.cinemaproject.entity.Actor;
import com.cinema.cinemaproject.entity.Director;
import com.cinema.cinemaproject.entity.Movie;
import com.cinema.cinemaproject.mapstruct.dtos.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface MapStructMapper {
    MovieDto movieToMovieDto(Movie movie);

    MovieAllDto movieToMovieAllDto(Movie movie);

    DirectorDto directorToDirectorDto(Director director);

    DirectorAllDto directorToDirectorAllDto(Director director);

    List<DirectorAllDto> directorsToDirectorsAllDtos(List<Director> directors);

    ActorDto actorToActorDto(Actor actor);

    ActorAllDto actorToActorAllDto(Actor actor);

    List<ActorAllDto> actorsToActorsAllDtos(List<Actor> actors);

    Movie movieDtoToMovie(MovieDto movieDto);

    Movie movieAllDtoToMovie(MovieAllDto movieDto);

    Director directorDtoToDirector(DirectorDto directorDto);

    Director directorAllDtoToDirector(DirectorAllDto directorAllDto);

    Actor actorDtoToActor(ActorDto actorDto);

    Actor actorAllDtoToActor(ActorAllDto actorAllDto);
}
