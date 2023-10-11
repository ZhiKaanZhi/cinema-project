package com.staff.staffmanagement.entity.enums;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum Genre implements Serializable {

    ACTION("ACTION"),
    ADVENTURE("ADVENTURE"),
    ANIMATION("ANIMATION"),
    COMEDY("COMEDY"),
    DRAMA("DRAMA"),
    HORROR("HORROR"),
    SCIFI("SCIFI"),
    THRILLER("THRILLER"),
    ROMANCE("ROMANCE");

    private static final long serialVersionUID = 1L;

    private final String genre;

    Genre(String genre) {
        this.genre = genre;
    }

}
