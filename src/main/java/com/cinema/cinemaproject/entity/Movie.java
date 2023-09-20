package com.cinema.cinemaproject.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int movieID;

    @Column(name = "title")
    private String movieTitle;

    @Column(name = "description")
    private String movieDescription;

    @ManyToOne(cascade =
            {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "director_id")
    private Director movieDirector;

    @Column(name = "durationInMin")
    private int movieDurationInMin;

    public Movie() {
    }

    public Movie(String movieTitle, String movieDescription, Director movieDirector, int movieDurationInMin) {
        this.movieTitle = movieTitle;
        this.movieDescription = movieDescription;
        this.movieDirector = movieDirector;
        this.movieDurationInMin = movieDurationInMin;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "movieID=" + movieID +
                ", movieTitle='" + movieTitle + '\'' +
                ", movieDescription='" + movieDescription + '\'' +
                ", movieDirector=" + movieDirector +
                ", movieDurationInMin=" + movieDurationInMin +
                '}';
    }
}
