package com.cinema.cinemaproject.mapstruct.dtos;

import com.cinema.cinemaproject.entity.Actor;
import com.cinema.cinemaproject.entity.Director;
import com.cinema.cinemaproject.entity.enums.Genre;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;


@Getter
@Setter
public class MovieAllDto {

    @JsonProperty("movie_id")
    private int movieID;

    @JsonProperty("movie_title")
    private String movieTitle;

    @JsonProperty("movie_description")
    private String movieDescription;

    @JsonProperty("movie_duration_in_min")
    private int movieDurationInMin;

    @JsonProperty("movie_ticket_price")
    private long movieTicketPrice;

    @JsonProperty("movie_release_date")
    private Date movieReleaseDate;

    @JsonProperty("movie_genre")
    private Genre movieGenre;

    @JsonProperty("movie_director")
    private Director movieDirector;

    @JsonProperty("movie_actors")
    private Set<Actor> movieActors;

    public MovieAllDto(String movieTitle, String movieDescription, int movieDurationInMin, long movieTicketPrice, Date movieReleaseDate, Genre movieGenre, Director movieDirector, Set<Actor> movieActors) {
        this.movieTitle = movieTitle;
        this.movieDescription = movieDescription;
        this.movieDurationInMin = movieDurationInMin;
        this.movieTicketPrice = movieTicketPrice;
        this.movieReleaseDate = movieReleaseDate;
        this.movieGenre = movieGenre;
        this.movieDirector = movieDirector;
        this.movieActors = movieActors;
    }
}
