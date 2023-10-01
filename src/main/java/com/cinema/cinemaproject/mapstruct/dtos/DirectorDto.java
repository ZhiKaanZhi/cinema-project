package com.cinema.cinemaproject.mapstruct.dtos;


import com.cinema.cinemaproject.entity.enums.Country;
import com.cinema.cinemaproject.entity.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor  // generates a default no-arg constructor
@AllArgsConstructor  // generates an all-args constructor
@ToString            // generates a toString method
public class DirectorDto {

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


}
