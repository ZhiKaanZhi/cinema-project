package com.cinema.cinemaproject;

import com.cinema.cinemaproject.entity.Actor;
import com.cinema.cinemaproject.entity.Director;
import com.cinema.cinemaproject.entity.Movie;
import com.cinema.cinemaproject.entity.enums.Country;
import com.cinema.cinemaproject.entity.enums.Gender;
import com.cinema.cinemaproject.entity.enums.Genre;
import com.cinema.cinemaproject.mapstruct.mappers.MapStructMapper;
import com.cinema.cinemaproject.service.ActorService;
import com.cinema.cinemaproject.service.DirectorService;
import com.cinema.cinemaproject.service.MovieService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

import static com.cinema.cinemaproject.entity.enums.Country.USA;

@SpringBootApplication
public class CinemaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(MovieService movieService, DirectorService directorService, ActorService actorService, MapStructMapper mapStructMapper) {

		return runner -> {

			addExampleMovies(movieService, directorService, actorService, mapStructMapper);

		};
	}

	private void addExampleMovies(MovieService movieService, DirectorService directorService, ActorService actorService, MapStructMapper mapStructMapper) {
		// create a Movie
		Movie tempMovie = new Movie("Star Wars", "Pew Pew", 120, 8, new Date(1977, 5, 04), Genre.SCIFI);

		// create the Director
		Director tempDirector = new Director("George Lucas", Country.USA, new Date(1944, 5, 14), Gender.MALE);

		// create the Actors
		Actor tempActor1 = new Actor("Mark Hamill", new Date(1951, 9, 25), Gender.MALE, USA);
		Actor tempActor2 = new Actor("Harrison Ford", new Date(1942, 7, 13), Gender.MALE, USA);
		Actor tempActor3 = new Actor("Carrie Fisher", new Date(1956, 10, 21), Gender.FEMALE, USA);



		//add actors to the movie
		tempMovie.addActor(tempActor1);
		tempMovie.addActor(tempActor2);
		tempMovie.addActor(tempActor3);
		movieService.save(mapStructMapper.movieToMovieAllDto(tempMovie));
		System.out.println("Saving the movie: " + tempMovie);

		// add movie to the director
		tempDirector.addMovies(tempMovie);
		directorService.save(tempDirector);
		System.out.println("Saving the director: " + tempDirector);


		//add movie to the actors
		tempActor1.addMovie(tempMovie);
		tempActor2.addMovie(tempMovie);
		tempActor3.addMovie(tempMovie);

		actorService.save(tempActor1);
		actorService.save(tempActor2);
		actorService.save(tempActor3);


		System.out.println("Saving the Actor: " + tempActor1);
		System.out.println("Saving the Actor: " + tempActor2);
		System.out.println("Saving the Actor: " + tempActor3);






		System.out.println("Done!");
	}

}
