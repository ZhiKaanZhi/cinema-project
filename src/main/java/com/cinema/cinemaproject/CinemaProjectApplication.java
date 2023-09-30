package com.cinema.cinemaproject;

import com.cinema.cinemaproject.entity.Actor;
import com.cinema.cinemaproject.entity.Director;
import com.cinema.cinemaproject.entity.Movie;
import com.cinema.cinemaproject.entity.enums.Country;
import com.cinema.cinemaproject.entity.enums.Gender;
import com.cinema.cinemaproject.entity.enums.Genre;
import com.cinema.cinemaproject.mapstruct.dtos.ActorDto;
import com.cinema.cinemaproject.mapstruct.dtos.DirectorDto;
import com.cinema.cinemaproject.mapstruct.dtos.MovieDto;
import com.cinema.cinemaproject.mapstruct.mappers.MapStructMapper;
import com.cinema.cinemaproject.service.ActorService;
import com.cinema.cinemaproject.service.DirectorService;
import com.cinema.cinemaproject.service.MovieService;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.cinema.cinemaproject.entity.enums.Country.USA;

@SpringBootApplication
public class CinemaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaProjectApplication.class, args);
	}

	@Bean
	@Transactional
	public CommandLineRunner commandLineRunner(MovieService movieService, DirectorService directorService, ActorService actorService, MapStructMapper mapStructMapper) {

		return runner -> {

			addExampleMovies(movieService, directorService, actorService, mapStructMapper);

		};
	}


	private void addExampleMovies(MovieService movieService, DirectorService directorService, ActorService actorService, MapStructMapper mapStructMapper) {
		// create Movies
		Movie tempMovie1 = new Movie("Star Wars", "Pew Pew", 120, 8, java.sql.Date.valueOf("1977-5-4"), Genre.SCIFI);
		Movie tempMovie2 = new Movie("Indiana Jones", "LASO", 110, 9, java.sql.Date.valueOf("1981-5-4"), Genre.ADVENTURE);
		Movie tempMovie3 = new Movie("Blah", "blah blah", 110, 9, java.sql.Date.valueOf("1999-5-4"), Genre.ADVENTURE);

		// create Directors
		Director tempDirector1 = new Director("George Lucas", Country.USA, java.sql.Date.valueOf("1944-5-14"), Gender.MALE);
		Director tempDirector2 = new Director("Blah director", Country.USA, java.sql.Date.valueOf("1944-5-14"), Gender.MALE);

		// create Actors
		Actor tempActor1 = new Actor("Mark Hamill", java.sql.Date.valueOf("1951-9-25"), Gender.MALE, USA);
		Actor tempActor2 = new Actor("Harrison Ford", java.sql.Date.valueOf("1942-7-13"), Gender.MALE, USA);
		Actor tempActor3 = new Actor("Carrie Fisher", java.sql.Date.valueOf("1956-10-21"), Gender.FEMALE, USA);
		Actor tempActor4 = new Actor("Blah Actor", java.sql.Date.valueOf("1956-10-21"), Gender.FEMALE, USA);

		// Add actors to movies
		tempMovie1.addActor(tempActor1);
		tempMovie1.addActor(tempActor2);
		tempMovie1.addActor(tempActor3);

		tempMovie2.addActor(tempActor2);

		tempMovie3.addActor(tempActor4);

		// Set movie directors
		tempMovie1.setMovieDirector(tempDirector1);
		tempMovie2.setMovieDirector(tempDirector1);
		tempMovie3.setMovieDirector(tempDirector2);

		// Add movies to directors
		tempDirector1.addMovies(tempMovie1);
		tempDirector1.addMovies(tempMovie2);

		tempDirector2.addMovies(tempMovie3);

		// Add movies to actors
		tempActor1.addMovie(tempMovie1);
		tempActor2.addMovie(tempMovie1);
		tempActor2.addMovie(tempMovie2);
		tempActor3.addMovie(tempMovie1);
		tempActor4.addMovie(tempMovie3);

		// Save movies
		MovieDto savedMovie1 = movieService.save(mapStructMapper.movieToMovieAllDto(tempMovie1));
		System.out.println("Saved movie with ID: " + savedMovie1);

		MovieDto savedMovie2 = movieService.save(mapStructMapper.movieToMovieAllDto(tempMovie2));
		System.out.println("Saved movie with ID: " + savedMovie2);

		MovieDto savedMovie3 = movieService.save(mapStructMapper.movieToMovieAllDto(tempMovie3));
		System.out.println("Saved movie with ID: " + savedMovie3);

		// Save directors
		DirectorDto savedDirector1 = directorService.save(mapStructMapper.directorToDirectorAllDto(tempDirector1));
		System.out.println("Saving the director: " + savedDirector1);

		DirectorDto savedDirector2 = directorService.save(mapStructMapper.directorToDirectorAllDto(tempDirector2));
		System.out.println("Saving the director: " + savedDirector2);

		// Save actors
		ActorDto savedActor1 = actorService.save(mapStructMapper.actorToActorAllDto(tempActor1));
		System.out.println("Actor saved: " + savedActor1);

		ActorDto savedActor2 = actorService.save(mapStructMapper.actorToActorAllDto(tempActor2));
		System.out.println("Actor saved: " + savedActor2);

		ActorDto savedActor3 = actorService.save(mapStructMapper.actorToActorAllDto(tempActor3));
		System.out.println("Actor saved: " + savedActor3);

		ActorDto savedActor4 = actorService.save(mapStructMapper.actorToActorAllDto(tempActor4));
		System.out.println("Actor saved: " + savedActor4);

		System.out.println("Done!");
	}

}
