package com.cinema.cinemaproject.ServiceContracts.dto;

import com.cinema.cinemaproject.entity.Movie;
import com.cinema.cinemaproject.entity.enums.Country;
import com.cinema.cinemaproject.entity.enums.Gender;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ActorDTO {

    private int actorID;
    private String actorName;
    private Country actorNationality;
    private int actorAge;
    private Gender actorGender;

    private Set<Movie> actorMovies;

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

    public Set<Movie> getActorMovies() {
        return actorMovies;
    }

    public void setActorMovies(Set<Movie> actorMovies) {
        this.actorMovies = actorMovies;
    }
}
