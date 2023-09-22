package com.cinema.cinemaproject.components;


import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class AgeCalculator {

    public static <LocalDate> int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between((java.time.LocalDate) birthDate, (java.time.LocalDate) currentDate).getYears();
        } else {
            return 0;
        }
    }
}
