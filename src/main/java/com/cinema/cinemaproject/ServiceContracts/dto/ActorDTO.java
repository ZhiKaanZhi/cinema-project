package com.cinema.cinemaproject.ServiceContracts.dto;

import com.cinema.cinemaproject.entity.Movie;
import com.cinema.cinemaproject.entity.enums.Country;
import com.cinema.cinemaproject.entity.enums.Gender;

import java.util.Set;

public class ActorDTO {

    private int actorID;
    private String actorName;
    private Country actorNationality;
    private int actorAge;
    private Gender actorGender;

    private Set<Movie> actorMovie;

    public ActorDTO() {
    }

    public int getActorID() {
        return actorID;
    }

    public void setActorID(int actorID) {
        this.actorID = actorID;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public Country getActorNationality() {
        return actorNationality;
    }

    public void setActorNationality(Country actorNationality) {
        this.actorNationality = actorNationality;
    }

    public int getActorAge() {
        return actorAge;
    }

    public void setActorAge(int actorAge) {
        this.actorAge = actorAge;
    }

    public Gender getActorGender() {
        return actorGender;
    }

    public void setActorGender(Gender actorGender) {
        this.actorGender = actorGender;
    }

    public Set<Movie> getActorMovie() {
        return actorMovie;
    }

    public void setActorMovie(Set<Movie> actorMovie) {
        this.actorMovie = actorMovie;
    }
}
