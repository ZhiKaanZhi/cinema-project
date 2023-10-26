package com.staff.staffmanagement.mapstruct.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.staff.staffmanagement.entity.enums.Genre;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor  // generates a default no-arg constructor
@AllArgsConstructor  // generates an all-args constructor
public class MovieDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("movie_id")
    private int movieID;

    @NotBlank(message = "Title is mandatory")
    @JsonProperty("movie_title")
    private String movieTitle;

    @JsonProperty("movie_description")
    private String movieDescription;

    @JsonProperty("movie_duration_in_min")
    private int movieDurationInMin;

    @JsonProperty("movie_ticket_price")
    private long movieTicketPrice;

    @JsonProperty("movie_release_date")
    private Date movieReleaseDate;

    @JsonProperty("movie_genre")
    private Genre movieGenre;

    @JsonProperty("director_name")
    private String movieDirectorName;

    @JsonProperty("actor_names")
    private Set<String> movieActorNames;
}
