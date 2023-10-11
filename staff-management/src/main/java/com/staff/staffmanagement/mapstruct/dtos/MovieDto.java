package com.staff.staffmanagement.mapstruct.dtos;
import com.staff.staffmanagement.entity.enums.Genre;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor  // generates a default no-arg constructor
@AllArgsConstructor  // generates an all-args constructor
@ToString            // generates a toString method
public class MovieDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int movieID;
    private String movieTitle;
    private String movieDescription;
    private int movieDurationInMin;
    private long movieTicketPrice;
    private Date movieReleaseDate;
    private Genre movieGenre;
    private String movieDirectorName;
    private Set<String> movieActorNames;
}
