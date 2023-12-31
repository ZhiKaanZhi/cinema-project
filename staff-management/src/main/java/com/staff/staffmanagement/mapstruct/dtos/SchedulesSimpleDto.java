package com.staff.staffmanagement.mapstruct.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.*;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor  // generates a default no-arg constructor
@AllArgsConstructor  // generates an all-args constructor
public class SchedulesSimpleDto {

    @JsonProperty("schedule_id")
    private Integer scheduleID;

    @JsonProperty("schedule_movie_title")
    private String scheduleMovieTitle; // Assumed to reference a movie in another microservice.

    @JsonProperty("schedule_start_time")
    private Time scheduleStartTime;

    @JsonProperty("schedule_end_time")
    private Time scheduleEndTime;

    @JsonProperty("schedule_screen_date")
    private Date scheduleScreenDate;

    @JsonProperty("schedule_available_seats")
    private Integer scheduleAvailableSeats;

    @JsonProperty("schedule_total_seats")
    private Integer scheduleTotalSeats;
}
