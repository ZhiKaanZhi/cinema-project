package com.cinema.cinemaproject.ServiceContracts.dto;

import com.cinema.cinemaproject.entity.Director;
import com.cinema.cinemaproject.entity.Actor;

import java.util.Date;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class MovieDTO {

    private int movieID;
    private String movieTitle;
    private String movieDescription;
    private Director movieDirector;
    private int movieDurationInMin;
    private long movieTicketPrice;
    private Set<Actor> movieActors;
    private Date movieRealeaseDate;

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

    public long getMovieTicketPrice() {
        return movieTicketPrice;
    }

    public void setMovieTicketPrice(long movieTicketPrice) {
        this.movieTicketPrice = movieTicketPrice;
    }

    public Set<Actor> getMovieActors() {
        return movieActors;
    }

    public void setMovieActors(Set<Actor> movieActors) {
        this.movieActors = movieActors;
    }

    public Date getMovieRealeaseDate() {
        return movieRealeaseDate;
    }

    public void setMovieRealeaseDate(Date movieRealeaseDate) {
        this.movieRealeaseDate = movieRealeaseDate;
    }

    @Override
    public String toString() {
        return "MovieDTO{" +
                "movieID=" + movieID +
                ", movieTitle='" + movieTitle + '\'' +
                ", movieDescription='" + movieDescription + '\'' +
                ", movieDirector=" + movieDirector +
                ", movieDurationInMin=" + movieDurationInMin +
                ", movieTicketPrice=" + movieTicketPrice +
                ", movieActors=" + movieActors +
                ", movieRealeaseDate=" + movieRealeaseDate +
                '}';
    }
}

