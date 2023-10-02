package com.movies.moviesmanagement.entity;

import com.movies.moviesmanagement.entity.enums.Country;
import com.movies.moviesmanagement.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    public Actor(String actorName, Date actorDateOfBirth, Gender actorGender, Country actorNationality, Set<Movie> actorMovies) {
        this.actorName = actorName;
        this.actorDateOfBirth = actorDateOfBirth;
        this.actorGender = actorGender;
        this.actorNationality = actorNationality;
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
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check if the reference is to the same object
        if (obj == null || getClass() != obj.getClass()) return false; // Check if obj is null or not an instance of Actor

        Actor actor = (Actor) obj; // Cast obj to Actor

        return actorID == actor.actorID; // Check if actorID of both instances are the same
    }

    @Override
    public int hashCode() {
        return actorID; // Use actorID as the hash code
    }

}
