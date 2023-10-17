package com.staff.staffmanagement.entity;

import com.staff.staffmanagement.mapstruct.dtos.MovieDto;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;



@Entity
@ToString
@Setter
@Getter
@NoArgsConstructor// generates a default no-arg constructor
@Table(name = "schedules")
public class Schedules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Integer scheduleID;

    @Column(name = "movie_id")
    private int scheduleMovieId; // Assumed to reference a movie in another microservice.

    @Column(name = "schedule_start_time")
    private Time scheduleStartTime;

    @Column(name = "schedule_end_time")
    private Time scheduleEndTime;

    @Column(name = "schedule_screen_date")
    private Date scheduleScreenDate;

    @Column(name = "schedule_available_seats")
    private Integer scheduleAvailableSeats;

    @Column(name = "schedule_total_seats")
    private Integer scheduleTotalSeats;

    public Schedules(int scheduleMovieId, Time scheduleStartTime, Time scheduleEndTime, Date scheduleScreenDate, Integer scheduleAvailableSeats, Integer scheduleTotalSeats) {
        this.scheduleMovieId = scheduleMovieId;
        this.scheduleStartTime = scheduleStartTime;
        this.scheduleEndTime = scheduleEndTime;
        this.scheduleScreenDate = scheduleScreenDate;
        this.scheduleAvailableSeats = scheduleAvailableSeats;
        this.scheduleTotalSeats = scheduleTotalSeats;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedules schedules = (Schedules) o;
        return Objects.equals(scheduleID, schedules.scheduleID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scheduleID);
    }

}
