package com.cinema.cinemaproject;

import com.cinema.cinemaproject.entity.Actor;
import com.cinema.cinemaproject.entity.Director;
import com.cinema.cinemaproject.entity.Movie;
import com.cinema.cinemaproject.entity.enums.Country;
import com.cinema.cinemaproject.entity.enums.Gender;
import com.cinema.cinemaproject.entity.enums.Genre;
import com.cinema.cinemaproject.mapstruct.dtos.ActorDto;
import com.cinema.cinemaproject.mapstruct.dtos.DirectorDto;
import com.cinema.cinemaproject.mapstruct.dtos.MovieAllDto;
import com.cinema.cinemaproject.mapstruct.dtos.MovieDto;
import com.cinema.cinemaproject.mapstruct.mappers.ActorMapper;
import com.cinema.cinemaproject.mapstruct.mappers.DirectorMapper;
import com.cinema.cinemaproject.mapstruct.mappers.MovieMapper;
import com.cinema.cinemaproject.service.ActorService;
import com.cinema.cinemaproject.service.DirectorService;
import com.cinema.cinemaproject.service.MovieDirectorActorService;
import com.cinema.cinemaproject.service.MovieService;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.cinema.cinemaproject.entity.enums.Country.USA;

@SpringBootApplication
public class CinemaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaProjectApplication.class, args);
	}

	@Bean
	@Transactional
	public CommandLineRunner commandLineRunner(MovieDirectorActorService movieDirectorActorService, MovieService movieService, DirectorService directorService, ActorService actorService, MovieMapper movieMapper, DirectorMapper directorMapper, ActorMapper actorMapper) {

		return runner -> {

			addExampleMovies(movieDirectorActorService, movieService, directorService, actorService, movieMapper, directorMapper, actorMapper);

		};
	}


	private void addExampleMovies(MovieDirectorActorService movieDirectorActorService,
								  MovieService movieService, DirectorService directorService,
								  ActorService actorService, MovieMapper movieMapper,
								  DirectorMapper directorMapper, ActorMapper actorMapper) {

		// Create Directors
		Director tempDirector1 = new Director("George Lucas", Country.USA, java.sql.Date.valueOf("1944-5-14"), Gender.MALE);
		Director tempDirector2 = new Director("Blah director", Country.USA, java.sql.Date.valueOf("1944-5-14"), Gender.MALE);

		// Save directors to the database
		List<Director> directors = Arrays.asList(tempDirector1, tempDirector2);
		directors.forEach(director -> {
			DirectorDto savedDirectorDto = directorService.save(directorMapper.directorToDirectorAllDto(director));
			director.setDirectorID(savedDirectorDto.getDirectorID());
		});

		// Create Actors and Save them to the database
		Actor tempActor1 = new Actor("Mark Hamill", java.sql.Date.valueOf("1951-9-25"), Gender.MALE, USA);
		Actor tempActor2 = new Actor("Harrison Ford", java.sql.Date.valueOf("1942-7-13"), Gender.MALE, USA);
		Actor tempActor3 = new Actor("Carrie Fisher", java.sql.Date.valueOf("1956-10-21"), Gender.FEMALE, USA);
		Actor tempActor4 = new Actor("Blah Actor", java.sql.Date.valueOf("1956-10-21"), Gender.FEMALE, USA);

		List<Actor> actors = Arrays.asList(tempActor1, tempActor2, tempActor3, tempActor4);
		actors.forEach(actor -> {
			ActorDto savedActorDto = actorService.save(actorMapper.actorToActorAllDto(actor));
			actor.setActorID(savedActorDto.getActorID());
		});

		// Create Movies and Associate movies with directors & actors
		Movie tempMovie1 = new Movie("Star Wars", "Pew Pew", 120, 8, java.sql.Date.valueOf("1977-5-4"), Genre.SCIFI);
		tempMovie1.setMovieDirector(tempDirector1);
		tempMovie1.addActor(tempActor1);
		tempMovie1.addActor(tempActor2);
		tempMovie1.addActor(tempActor3);

		Movie tempMovie2 = new Movie("Indiana Jones", "Archeologist", 110, 9, java.sql.Date.valueOf("1981-5-4"), Genre.ADVENTURE);
		tempMovie2.setMovieDirector(tempDirector1);
		tempMovie2.addActor(tempActor2);

		Movie tempMovie3 = new Movie("Blah", "blah blah", 110, 9, java.sql.Date.valueOf("1999-5-4"), Genre.ADVENTURE);
		tempMovie3.setMovieDirector(tempDirector2);
		tempMovie3.addActor(tempActor4);

		// Save movies to the database WITH their associations
		List<Movie> movies = Arrays.asList(tempMovie1, tempMovie2, tempMovie3);
		movies.forEach(movie -> {
			MovieAllDto movieAllDto = movieMapper.movieToMovieAllDto(movie);
			if (movie.getMovieDirector() != null) {
				movieAllDto.setMovieDirectorId(movie.getMovieDirector().getDirectorID());
			}
			movieAllDto.setMovieActorIds(movie.getMovieActors().stream().map(Actor::getActorID).collect(Collectors.toSet()));
			movieDirectorActorService.saveMovieWithAssociations(movieAllDto);
		});

		System.out.println("Done!");
	}

}
