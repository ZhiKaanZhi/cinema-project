package com.cinema.cinemaproject.entity;

import com.cinema.cinemaproject.ServiceContracts.dto.DirectorDTO;
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
@Table(name = "director")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "director_id")
    private int directorID;

    @Column(name = "director_name")
    private String directorName;

    @Column(name = "director_nationality")
    private Country directorNationality;

    @Column(name = "director_date_of_birth")
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date directorDateOfBirth;

    @Column(name = "director_gender")
    private Gender directorGender;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movieDirector")
    private Set<Movie> directorMovies;

    public Director() {
    }

    public Director(String directorName, Country directorNationality, Date directorDateOfBirth, Gender directorGender) {
        this.directorName = directorName;
        this.directorNationality = directorNationality;
        this.directorDateOfBirth = directorDateOfBirth;
        this.directorGender = directorGender;
    }

    public int getDirectorID() {
        return directorID;
    }

    public void setDirectorID(int directorID) {
        this.directorID = directorID;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public Country getDirectorNationality() {
        return directorNationality;
    }

    public void setDirectorNationality(Country directorNationality) {
        this.directorNationality = directorNationality;
    }

    public Date getDirectorDateOfBirth() {
        return directorDateOfBirth;
    }

    public void setDirectorDateOfBirth(Date directorDateOfBirth) {
        this.directorDateOfBirth = directorDateOfBirth;
    }

    public Gender getDirectorGender() {
        return directorGender;
    }

    public void setDirectorGender(Gender directorGender) {
        this.directorGender = directorGender;
    }

    public Set<Movie> getDirectorMovies() {
        return directorMovies;
    }

    public void setDirectorMovies(Set<Movie> directorMovies) {
        this.directorMovies = directorMovies;
    }

    @Override
    public String toString() {
        return "Director{" +
                "directorID=" + directorID +
                ", directorName='" + directorName + '\'' +
                ", directorNationality=" + directorNationality +
                ", directorDateOfBirth=" + directorDateOfBirth +
                ", directorGender=" + directorGender +
                ", directorMovies=" + directorMovies +
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

    public static DirectorDTO toDirectorDTO(Optional<Director> director) {

        if (director.isEmpty()) {
            return null; // Handle null input gracefully if needed
        }


        DirectorDTO directorDTO = new DirectorDTO();
        directorDTO.setDirectorID(director.get().getDirectorID());
        directorDTO.setDirectorName(director.get().getDirectorName());
        directorDTO.setDirectorNationality(director.get().getDirectorNationality());
        directorDTO.setDirectorAge(AgeCalculator.calculateAge(LocalDate.now(),director.get().getDirectorDateOfBirth()));
        directorDTO.setDirectorMovies(director.get().getDirectorMovies());

        return directorDTO;
    }
}
