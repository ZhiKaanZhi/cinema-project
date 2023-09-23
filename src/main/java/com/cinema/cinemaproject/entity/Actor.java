package com.cinema.cinemaproject.entity;

import com.cinema.cinemaproject.ServiceContracts.dto.ActorDTO;
import com.cinema.cinemaproject.components.AgeCalculator;
import com.cinema.cinemaproject.entity.enums.Country;
import com.cinema.cinemaproject.entity.enums.Gender;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actorID")
    private int actorID;

    @Column(name = "actorName")
    private String actorName;

    @Column(name = "actorDateOfBirth")
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date actorDateOfBirth;

    @Column(name = "actorGender")
    private Gender actorGender;

    @Column(name = "actorNationality")
    private Country actorNationality;

    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "actorID"),
            inverseJoinColumns = @JoinColumn(name = "movieID"))
    private Set<Movie> actorMovies;

    public Actor() {
    }

    public Actor(String actorName, Date actorDateOfBirth, Gender actorGender, Country actorNationality) {
        this.actorName = actorName;
        this.actorDateOfBirth = actorDateOfBirth;
        this.actorGender = actorGender;
        this.actorNationality = actorNationality;
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

    public Date getActorDateOfBirth() {
        return actorDateOfBirth;
    }

    public void setActorDateOfBirth(Date actorDateOfBirth) {
        this.actorDateOfBirth = actorDateOfBirth;
    }

    public Gender getActorGender() {
        return actorGender;
    }

    public void setActorGender(Gender actorGender) {
        this.actorGender = actorGender;
    }

    public Country getActorNationality() {
        return actorNationality;
    }

    public void setActorNationality(Country actorNationality) {
        this.actorNationality = actorNationality;
    }

    public Set<Movie> getActorMovies() {
        return actorMovies;
    }

    public void setActorMovies(Set<Movie> actorMovies) {
        this.actorMovies = actorMovies;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "actorID=" + actorID +
                ", actorName='" + actorName + '\'' +
                ", actorDateOfBirth=" + actorDateOfBirth +
                ", actorGender=" + actorGender +
                ", actorNationality=" + actorNationality +
                ", actorMovies=" + actorMovies +
                '}';
    }

    public void addMovie(Movie movie) {
        if (actorMovies == null) {
            actorMovies = new HashSet<Movie>();
        }
        else {
            actorMovies.add(movie);
        }
    }

    public static ActorDTO toActorDTO(Optional<Actor> actor) {
        if (actor.isEmpty()) {
            return null; // Handle null input gracefully if needed
        }


        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setActorID(actor.get().getActorID());
        actorDTO.setActorName(actor.get().getActorName());
        actorDTO.setActorGender(actor.get().getActorGender());
        actorDTO.setActorNationality(actor.get().getActorNationality());
        actorDTO.setActorAge(AgeCalculator.calculateAge(LocalDate.now(),actor.get().getActorDateOfBirth()));


        return actorDTO;
    }
}
