package com.cinema.cinemaproject.entity;

import com.cinema.cinemaproject.entity.enums.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int movieID;

    @Column(name = "movie_title")
    private String movieTitle;

    @Column(name = "movie_description")
    private String movieDescription;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "director_id")
    private Director movieDirector;

    @ManyToMany(cascade = { CascadeType.PERSIST })
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<Actor> movieActors = new HashSet<>(); // Initialize the set with an empty HashSet;

    @Column(name = "movie_duration_in_min")
    private int movieDurationInMin;

    @Column(name = "movie_price")
    private long movieTicketPrice;

    @Column(name = "movie_release_date")
    //@DateTimeFormat(pattern="dd/MM/yyyy")
    private Date movieReleaseDate;

    @Column(name = "movie_genre")
    @Enumerated(EnumType.STRING)
    private Genre movieGenre;

    public Movie() {
    }

    public Movie(String movieTitle, String movieDescription, int movieDurationInMin, long movieTicketPrice, Date movieReleaseDate, Genre movieGenre) {
        this.movieTitle = movieTitle;
        this.movieDescription = movieDescription;
        this.movieDurationInMin = movieDurationInMin;
        this.movieTicketPrice = movieTicketPrice;
        this.movieReleaseDate = movieReleaseDate;
        this.movieGenre = movieGenre;
    }

    public Movie(String movieTitle, String movieDescription, Director movieDirector, Set<Actor> movieActors, int movieDurationInMin, long movieTicketPrice, Date movieReleaseDate, Genre movieGenre) {
        this.movieTitle = movieTitle;
        this.movieDescription = movieDescription;
        this.movieDirector = movieDirector;
        this.movieActors = movieActors;
        this.movieDurationInMin = movieDurationInMin;
        this.movieTicketPrice = movieTicketPrice;
        this.movieReleaseDate = movieReleaseDate;
        this.movieGenre = movieGenre;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieID=" + movieID +
                ", movieTitle='" + movieTitle + '\'' +
                ", movieDescription='" + movieDescription + '\'' +
                ", movieDirector=" + (movieDirector != null ? movieDirector.getDirectorName() : "null") +
                ", movieActors=" + movieActors +
                ", movieDurationInMin=" + movieDurationInMin +
                ", movieTicketPrice=" + movieTicketPrice +
                ", movieReleaseDate=" + movieReleaseDate +
                '}';
    }

    public void addActor(Actor actor) {
        if (movieActors == null) {
            movieActors = new HashSet<Actor>();
        }
        System.out.println("Before adding: " + movieActors.size());
        movieActors.add(actor); // Add the actor to the movie's set
        System.out.println("After adding: " + movieActors.size());
        actor.getActorMovies().add(this); // Add the movie to the actor's set
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(movieID, movie.movieID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieID);
    }

}
