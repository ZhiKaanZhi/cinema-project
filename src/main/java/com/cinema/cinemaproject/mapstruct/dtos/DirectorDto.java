package com.cinema.cinemaproject.mapstruct.dtos;


import com.cinema.cinemaproject.entity.enums.Country;
import com.cinema.cinemaproject.entity.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    @Override
    public String toString() {
        return "DirectorDto{" +
                "directorID=" + directorID +
                ", directorName='" + directorName + '\'' +
                ", directorNationality=" + directorNationality +
                ", directorAge=" + directorAge +
                ", directorGender=" + directorGender +
                '}';
    }
}
