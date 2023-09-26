package com.cinema.cinemaproject.entity;

import com.cinema.cinemaproject.entity.enums.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
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

    @ManyToOne(cascade =
            {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "director_id")
    private Director movieDirector;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "actorMovies")
    private Set<Actor> movieActors;

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

    @Override
    public String toString() {
        return "Movie{" +
                "movieID=" + movieID +
                ", movieTitle='" + movieTitle + '\'' +
                ", movieDescription='" + movieDescription + '\'' +
                ", movieDirector=" + movieDirector +
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
        else {
            movieActors.add(actor);
        }
    }


}
