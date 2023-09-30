package com.cinema.cinemaproject.mapstruct.dtos;

import com.cinema.cinemaproject.entity.Movie;
import com.cinema.cinemaproject.entity.enums.Country;
import com.cinema.cinemaproject.entity.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
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

    @JsonProperty("director_movies")
    private Set<Movie> directorMovies;

    @Override
    public String toString() {
        return "DirectorAllDto{" +
                "directorID=" + directorID +
                ", directorName='" + directorName + '\'' +
                ", directorNationality=" + directorNationality +
                ", directorAge=" + directorAge +
                ", directorGender=" + directorGender +
                ", directorMovies=" + directorMovies +
                '}';
    }
}
