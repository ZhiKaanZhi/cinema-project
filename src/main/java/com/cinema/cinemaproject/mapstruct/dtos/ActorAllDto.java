package com.cinema.cinemaproject.mapstruct.dtos;

import com.cinema.cinemaproject.entity.Movie;
import com.cinema.cinemaproject.entity.enums.Country;
import com.cinema.cinemaproject.entity.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor  // generates a default no-arg constructor
@AllArgsConstructor  // generates an all-args constructor
@ToString            // generates a toString method
public class ActorAllDto {

    @JsonProperty("actor_id")
    private int actorID;

    @JsonProperty("actor_name")
    private String actorName;

    @JsonProperty("actor_nationality")
    private Country actorNationality;

    @JsonProperty("actor_date_of_birth")
    private Date actorDateOfBirth;

    @JsonProperty("actor_gender")
    private Gender actorGender;

    @JsonProperty("actor_movie_ids")
    private Set<Integer> actorMovieIds;  // Changed from Set<Movie> to Set<Integer>
}
