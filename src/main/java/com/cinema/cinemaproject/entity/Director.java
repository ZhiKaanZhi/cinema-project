package com.cinema.cinemaproject.entity;

import com.cinema.cinemaproject.ServiceContracts.dto.DirectorDTO;
import com.cinema.cinemaproject.ServiceContracts.dto.MovieDTO;
import com.cinema.cinemaproject.entity.enums.Country;
import jakarta.persistence.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Entity
@Table(name = "director")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int directorID;

    @Column(name = "name")
    private String directorName;

    @Column(name = "nationality")
    private Country directorNationality;

    public Director() {
    }

    public Director(String directorName, Country directorNationality) {
        this.directorName = directorName;
        this.directorNationality = directorNationality;
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

    @Override
    public String toString() {
        return "Director{" +
                "directorID=" + directorID +
                ", directorName='" + directorName + '\'' +
                ", directorNationality=" + directorNationality +
                '}';
    }


    public static DirectorDTO toMovieDTO(Optional<Director> director) {

        if (director.isEmpty()) {
            return null; // Handle null input gracefully if needed
        }


        DirectorDTO directorDTO = new DirectorDTO();
        directorDTO.setDirectorID(director.get().getDirectorID());
        directorDTO.setDirectorName(director.get().getDirectorName());
        directorDTO.setDirectorNationality(director.get().getDirectorNationality());

        return directorDTO;
    }
}
