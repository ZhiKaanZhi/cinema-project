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
		Movie tempMovie1 = new Movie("Star Wars", "Pew Pew", 120, 8, new Date(1977, 5, 4), Genre.SCIFI);
		Movie tempMovie2 = new Movie("Indiana Jones", "LASO", 110, 9, new Date(1981, 5, 4), Genre.ADVENTURE);
		Movie tempMovie3 = new Movie("Blah", "blah blah", 110, 9, new Date(1999, 5, 4), Genre.ADVENTURE);

		// create the Director
		Director tempDirector1 = new Director("George Lucas", Country.USA, new Date(1944, 5, 14), Gender.MALE);
		Director tempDirector2 = new Director("Blah director", Country.USA, new Date(1944, 5, 14), Gender.MALE);

		// create the Actors
		Actor tempActor1 = new Actor("Mark Hamill", new Date(1951, 9, 25), Gender.MALE, USA);
		Actor tempActor2 = new Actor("Harrison Ford", new Date(1942, 7, 13), Gender.MALE, USA);
		Actor tempActor3 = new Actor("Carrie Fisher", new Date(1956, 10, 21), Gender.FEMALE, USA);
		Actor tempActor4 = new Actor("Blah Actor", new Date(1956, 10, 21), Gender.FEMALE, USA);



		//add actors to the movie
		tempMovie1.addActor(tempActor1);
		tempMovie1.addActor(tempActor2);
		tempMovie1.addActor(tempActor3);
		tempMovie1.setMovieDirector(tempDirector1);


		// add movie to the director
		tempDirector1.addMovies(tempMovie1);
		tempDirector1.addMovies(tempMovie2);


		tempMovie2.addActor(tempActor2);
		tempMovie2.setMovieDirector(tempDirector1);


		tempMovie3.addActor(tempActor4);
		tempMovie3.setMovieDirector(tempDirector2);




		tempDirector2.addMovies(tempMovie3);



		//add movie to the actors
		tempActor1.addMovie(tempMovie1);
		tempActor2.addMovie(tempMovie1);
		tempActor2.addMovie(tempMovie2);
		tempActor3.addMovie(tempMovie1);
		tempActor4.addMovie(tempMovie3);



		movieService.save(mapStructMapper.movieToMovieAllDto(tempMovie1));
		System.out.println("Saving the movie: " + tempMovie1);

		movieService.save(mapStructMapper.movieToMovieAllDto(tempMovie2));
		System.out.println("Saving the movie: " + tempMovie2);

		movieService.save(mapStructMapper.movieToMovieAllDto(tempMovie3));
		System.out.println("Saving the movie: " + tempMovie3);

		directorService.save(mapStructMapper.directorToDirectorAllDto(tempDirector1));
		System.out.println("Saving the director: " + tempDirector1);

		directorService.save(mapStructMapper.directorToDirectorAllDto(tempDirector2));
		System.out.println("Saving the director: " + tempDirector2);





		actorService.save(mapStructMapper.actorToActorAllDto(tempActor1));
		actorService.save(mapStructMapper.actorToActorAllDto(tempActor2));
		actorService.save(mapStructMapper.actorToActorAllDto(tempActor3));
		actorService.save(mapStructMapper.actorToActorAllDto(tempActor4));


		System.out.println("Saving the Actor: " + tempActor1);
		System.out.println("Saving the Actor: " + tempActor2);
		System.out.println("Saving the Actor: " + tempActor3);
		System.out.println("Saving the Actor: " + tempActor4);

		System.out.println("Done!");
	}

}
