package com.cinema.cinemaproject.entity;

import com.cinema.cinemaproject.mapstruct.dtos.ActorDto;
import com.cinema.cinemaproject.entity.enums.Country;
import com.cinema.cinemaproject.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private int actorID;

    @Column(name = "actor_name")
    private String actorName;

    @Column(name = "actor_date_of_birth")
    //@DateTimeFormat(pattern="dd/MM/yyyy")
    private Date actorDateOfBirth;

    @Column(name = "actor_gender")
    @Enumerated(EnumType.STRING)
    private Gender actorGender;

    @Column(name = "actor_nationality")
    @Enumerated(EnumType.STRING)
    private Country actorNationality;

    @ManyToMany(mappedBy = "movieActors", cascade = CascadeType.PERSIST)
    private Set<Movie> actorMovies = new HashSet<>(); // Initialize the set with an empty HashSet;

    public Actor() {
    }

    public Actor(String actorName, Date actorDateOfBirth, Gender actorGender, Country actorNationality) {
        this.actorName = actorName;
        this.actorDateOfBirth = actorDateOfBirth;
        this.actorGender = actorGender;
        this.actorNationality = actorNationality;
    }



    @Override
    public String toString() {
        return "Actor{" +
                "actorID=" + actorID +
                ", actorName='" + actorName + '\'' +
                ", actorDateOfBirth=" + actorDateOfBirth +
                ", actorGender=" + actorGender +
                ", actorNationality=" + actorNationality +
                //", actorMovies=" + actorMovies +
                '}';
    }

    public void addMovie(Movie movie) {
        if (actorMovies == null) {
            actorMovies = new HashSet<Movie>();
        }
        actorMovies.add(movie); // Add the movie to the actor's set
        movie.getMovieActors().add(this); // Add the actor to the movie's set
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Objects.equals(actorID, actor.actorID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorID);
    }

}
