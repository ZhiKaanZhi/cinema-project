package com.cinema.cinemaproject.entity.enums;

public enum Gender {
    MALE("MALE"), FEMALE("FEMALE"), OTHER("OTHERS");

    private String gender;
    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
