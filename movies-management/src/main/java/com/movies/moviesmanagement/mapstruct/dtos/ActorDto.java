package com.movies.moviesmanagement.mapstruct.dtos;

import com.movies.moviesmanagement.entity.enums.Country;
import com.movies.moviesmanagement.entity.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor  // generates a default no-arg constructor
@AllArgsConstructor  // generates an all-args constructor
@ToString            // generates a toString method
public class ActorDto {

    @JsonProperty("actor_id")
    private int actorID;

    @JsonProperty("actor_name")
    private String actorName;

    @JsonProperty("actor_nationality")
    private Country actorNationality;

    @JsonProperty("actor_date_of_birth")
    private Date actorDateOfBirth;

    @JsonProperty("actor_gender")
    private Gender actorGender;
}
