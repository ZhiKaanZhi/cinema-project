package com.cinema.cinemaproject.mapstruct.dtos;

import java.util.Date;
import java.util.Set;

import com.cinema.cinemaproject.entity.enums.Genre;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor  // generates a default no-arg constructor
@ToString            // generates a toString method
public class MovieDto {
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

    @JsonProperty("director_name")
    private String movieDirectorName;  // New field for director's name

    @JsonProperty("actor_names")
    private Set<String> movieActorNames;  // New field for actor names
}

