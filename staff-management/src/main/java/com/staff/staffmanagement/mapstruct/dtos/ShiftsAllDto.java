package com.staff.staffmanagement.mapstruct.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor  // generates a default no-arg constructor
@AllArgsConstructor  // generates an all-args constructor
public class ShiftsAllDto {

    @JsonProperty("shift_id")
    private Integer shiftID;

    @JsonProperty("shift_start_time")
    private Time shiftStartTime;

    @JsonProperty("shift_end_time")
    private Time shiftEndTime;

    @JsonProperty("shift_date")
    private Date shiftDate;

    @JsonProperty("shift_staff_ids")
    private List<Integer> shiftStaffIDs;

}
