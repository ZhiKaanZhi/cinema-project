package com.cinema.cinemaproject.entity;

import com.cinema.cinemaproject.mapstruct.dtos.DirectorAllDto;
import com.cinema.cinemaproject.components.AgeCalculator;
import com.cinema.cinemaproject.entity.enums.Country;
import com.cinema.cinemaproject.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "director")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "director_id")
    private int directorID;

    @Column(name = "director_name")
    private String directorName;

    @Column(name = "director_nationality")
    @Enumerated(EnumType.STRING)
    private Country directorNationality;

    @Column(name = "director_date_of_birth")
    //@DateTimeFormat(pattern="dd/MM/yyyy")
    private Date directorDateOfBirth;

    @Column(name = "director_gender")
    @Enumerated(EnumType.STRING)
    private Gender directorGender;

    @OneToMany(mappedBy = "movieDirector", cascade=CascadeType.ALL)
    private Set<Movie> directorMovies;

    public Director() {
    }

    public Director(String directorName, Country directorNationality, Date directorDateOfBirth, Gender directorGender) {
        this.directorName = directorName;
        this.directorNationality = directorNationality;
        this.directorDateOfBirth = directorDateOfBirth;
        this.directorGender = directorGender;
    }



    @Override
    public String toString() {
        return "Director{" +
                "directorID=" + directorID +
                ", directorName='" + directorName + '\'' +
                ", directorNationality=" + directorNationality +
                ", directorDateOfBirth=" + directorDateOfBirth +
                ", directorGender=" + directorGender +
                ", directorMovies=" + (directorMovies != null ? directorMovies.toString() : "null") +
                '}';
    }

    public void addMovies(Movie movie) {
        if (directorMovies == null) {
            directorMovies = new HashSet<Movie>();
        }
        else {
            directorMovies.add(movie);
            movie.setMovieDirector(this);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director director = (Director) o;
        return Objects.equals(directorID, director.directorID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(directorID);
    }

}
