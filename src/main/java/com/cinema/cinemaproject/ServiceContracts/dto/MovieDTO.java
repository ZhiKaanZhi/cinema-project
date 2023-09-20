package com.cinema.cinemaproject.ServiceContracts.dto;

import com.cinema.cinemaproject.entity.Director;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class MovieDTO {

    private int movieID;
    private String movieTitle;
    private String movieDescription;
    private Director movieDirector;
    private int movieDurationInMin;

    public MovieDTO() {
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public Director getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(Director movieDirector) {
        this.movieDirector = movieDirector;
    }

    public int getMovieDurationInMin() {
        return movieDurationInMin;
    }

    public void setMovieDurationInMin(int movieDurationInMin) {
        this.movieDurationInMin = movieDurationInMin;
    }
}
