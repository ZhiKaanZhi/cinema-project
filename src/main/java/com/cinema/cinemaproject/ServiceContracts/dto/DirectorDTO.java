package com.cinema.cinemaproject.ServiceContracts.dto;

import com.cinema.cinemaproject.entity.Movie;
import com.cinema.cinemaproject.entity.enums.Country;
import com.cinema.cinemaproject.entity.enums.Gender;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DirectorDTO {

    private int directorID;
    private String directorName;
    private Country directorNationality;
    private int directorAge;
    private Gender directorGender;
    private Set<Movie> directorMovies;

    public DirectorDTO() {
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

    public int getDirectorAge() {
        return directorAge;
    }

    public void setDirectorAge(int directorAge) {
        this.directorAge = directorAge;
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
        return "DirectorDTO{" +
                "directorID=" + directorID +
                ", directorName='" + directorName + '\'' +
                ", directorNationality=" + directorNationality +
                ", directorAge=" + directorAge +
                ", directorGender=" + directorGender +
                ", directorMovies=" + directorMovies +
                '}';
    }
}
