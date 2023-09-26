package com.cinema.cinemaproject.entity.enums;

public enum Genre {

    ACTION("ACTION"),
    ADVENTURE("ADVENTURE"),
    ANIMATION("ANIMATION"),
    COMEDY("COMEDY"),
    DRAMA("DRAMA"),
    HORROR("HORROR"),
    SCIFI("SCIFI"),
    THRILLER("THRILLER"),
    ROMANCE("ROMANCE");

    private String genre;
    Genre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
}
