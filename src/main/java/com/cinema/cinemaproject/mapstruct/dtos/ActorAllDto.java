package com.cinema.cinemaproject.mapstruct.dtos;

import com.cinema.cinemaproject.entity.Movie;
import com.cinema.cinemaproject.entity.enums.Country;
import com.cinema.cinemaproject.entity.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
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

    @JsonProperty("actor_movies")
    private Set<Movie> actorMovies;

    @Override
    public String toString() {
        return "ActorAllDto{" +
                "actorID=" + actorID +
                ", actorName='" + actorName + '\'' +
                ", actorNationality=" + actorNationality +
                ", actorDateOfBirth=" + actorDateOfBirth +
                ", actorGender=" + actorGender +
                ", actorMovies=" + actorMovies +
                '}';
    }
}
