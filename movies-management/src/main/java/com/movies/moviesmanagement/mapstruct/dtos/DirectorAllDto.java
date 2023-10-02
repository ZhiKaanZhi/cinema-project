package com.movies.moviesmanagement.mapstruct.dtos;

import com.movies.moviesmanagement.entity.enums.Country;
import com.movies.moviesmanagement.entity.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor  // generates a default no-arg constructor
@AllArgsConstructor  // generates an all-args constructor
@ToString            // generates a toString method
public class DirectorAllDto {

    @JsonProperty("director_id")
    private int directorID;

    @JsonProperty("director_name")
    private String directorName;

    @JsonProperty("director_nationality")
    private Country directorNationality;

    @JsonProperty("director_age")
    private int directorAge;

    @JsonProperty("director_gender")
    private Gender directorGender;

    @JsonProperty("director_movie_ids")
    private Set<Integer> directorMovieIds;  // Changed from Set<Movie> to Set<Integer>

}
