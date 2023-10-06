package com.movies.moviesmanagement.mapstruct.dtos;

import com.movies.moviesmanagement.entity.enums.Genre;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;
import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor  // generates a default no-arg constructor
@AllArgsConstructor  // generates an all-args constructor
@ToString            // generates a toString method
public class MovieAllDto {

    @JsonProperty("movie_id")
    private int movieID;

    @NotBlank(message = "Title is mandatory")
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

    @JsonProperty("movie_director_id")
    private Integer movieDirectorId;  // Changed from Director object to ID

    @JsonProperty("movie_actor_ids")
    private Set<Integer> movieActorIds;  // Changed from Set<Actor> to Set<Integer>
}
