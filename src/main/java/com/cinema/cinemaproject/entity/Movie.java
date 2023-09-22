package com.cinema.cinemaproject.entity;
import com.cinema.cinemaproject.ServiceContracts.dto.MovieDTO;
import jakarta.persistence.*;

import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movieID")
    private int movieID;

    @Column(name = "movieTitle")
    private String movieTitle;

    @Column(name = "movieDescription")
    private String movieDescription;

    @ManyToOne(cascade =
            {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "directorID")
    private Director movieDirector;

    @ManyToMany(mappedBy = "actorMovies")
    private Set<Actor> movieActors;

    @Column(name = "movieDurationInMin")
    private int movieDurationInMin;

    @Column(name = "moviePrice")
    private long movieTicketPrice;

    public Movie() {
    }


    public Movie(String movieTitle, String movieDescription, Director movieDirector, Set<Actor> movieActors, int movieDurationInMin, long movieTicketPrice) {
        this.movieTitle = movieTitle;
        this.movieDescription = movieDescription;
        this.movieDirector = movieDirector;
        this.movieActors = movieActors;
        this.movieDurationInMin = movieDurationInMin;
        this.movieTicketPrice = movieTicketPrice;
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

    public Set<Actor> getMovieActors() {
        return movieActors;
    }

    public void setMovieActors(Set<Actor> movieActors) {
        this.movieActors = movieActors;
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
                '}';
    }

    public static MovieDTO toMovieDTO(Optional<Movie> movie) {
        if (movie.isEmpty()) {
            return null; // Handle null input gracefully if needed
        }


        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setMovieID(movie.get().getMovieID());
        movieDTO.setMovieTitle(movie.get().getMovieTitle());
        movieDTO.setMovieDescription(movie.get().getMovieDescription());
        movieDTO.setMovieDirector(movie.get().getMovieDirector());
        movieDTO.setMovieDurationInMin(movie.get().getMovieDurationInMin());
        movieDTO.setMovieTicketPrice(movie.get().getMovieTicketPrice());
        movieDTO.setMovieActors(movie.get().getMovieActors());

        return movieDTO;
    }
}
