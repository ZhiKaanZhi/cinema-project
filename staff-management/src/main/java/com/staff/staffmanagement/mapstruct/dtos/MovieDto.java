package com.staff.staffmanagement.mapstruct.dtos;
import com.movies.moviesmanagement.entity.enums.Genre;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor  // generates a default no-arg constructor
@AllArgsConstructor  // generates an all-args constructor
@ToString            // generates a toString method
public class MovieDto {
    private int movieID;
    private String movieTitle;
    private String movieDescription;
    private int movieDurationInMin;
    private long movieTicketPrice;
    private Date movieReleaseDate;
    private Genre movieGenre;
    private String movieDirectorName;  // New field for director's name
    private Set<String> movieActorNames;  // New field for actor names
}
