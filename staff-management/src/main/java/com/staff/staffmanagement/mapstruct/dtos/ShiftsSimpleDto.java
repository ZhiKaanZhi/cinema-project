package com.staff.staffmanagement.mapstruct.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.staff.staffmanagement.entity.Staff;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor  // generates a default no-arg constructor
@AllArgsConstructor  // generates an all-args constructor
public class ShiftsSimpleDto {

    @JsonProperty("shift_id")
    private Integer shiftID;

    @JsonProperty("shift_start_time")
    private Time shiftStartTime;

    @JsonProperty("shift_end_time")
    private Time shiftEndTime;

    @JsonProperty("shift_date")
    private Date shiftDate;
}
