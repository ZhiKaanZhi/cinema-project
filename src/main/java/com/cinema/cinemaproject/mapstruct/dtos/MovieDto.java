package com.cinema.cinemaproject.mapstruct.dtos;

import java.util.Date;

import com.cinema.cinemaproject.entity.enums.Genre;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    @Override
    public String toString() {
        return "MovieDto{" +
                "movieID=" + movieID +
                ", movieTitle='" + movieTitle + '\'' +
                ", movieDescription='" + movieDescription + '\'' +
                ", movieDurationInMin=" + movieDurationInMin +
                ", movieTicketPrice=" + movieTicketPrice +
                ", movieReleaseDate=" + movieReleaseDate +
                ", movieGenre=" + movieGenre +
                '}';
    }
}

