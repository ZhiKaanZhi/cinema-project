package com.cinema.cinemaproject.entity;

import com.cinema.cinemaproject.ServiceContracts.dto.DirectorDTO;
import com.cinema.cinemaproject.components.AgeCalculator;
import com.cinema.cinemaproject.entity.enums.Country;
import com.cinema.cinemaproject.entity.enums.Gender;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "director")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "directorID")
    private int directorID;

    @Column(name = "directorName")
    private String directorName;

    @Column(name = "directorNationality")
    private Country directorNationality;

    @Column(name = "directorDateOfBirth")
    private Date directorDateOfBirth;

    @Column(name = "directorGender")
    private Gender directorGender;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "director")
    private Set<Movie> directorMovies;

    public Director() {
    }

    public Director(String directorName, Country directorNationality, Date directorDateOfBirth, Gender directorGender, Set<Movie> directorMovies) {
        this.directorName = directorName;
        this.directorNationality = directorNationality;
        this.directorDateOfBirth = directorDateOfBirth;
        this.directorGender = directorGender;
        this.directorMovies = directorMovies;
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
