package com.cinema.cinemaproject.ServiceContracts.dto;

import com.cinema.cinemaproject.entity.enums.Country;


public class DirectorDTO {

    private int directorID;
    private String directorName;
    private Country directorNationality;

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

    @Override
    public String toString() {
        return "DirectorDTO{" +
                "directorID=" + directorID +
                ", directorName='" + directorName + '\'' +
                ", directorNationality=" + directorNationality +
                '}';
    }
}
