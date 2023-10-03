package com.staff.staffmanagement.mapstruct.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor  // generates a default no-arg constructor
@AllArgsConstructor  // generates an all-args constructor
public class StaffAllDto {

    @JsonProperty("staff_id")
    private int staffID;

    @JsonProperty("staff_name")
    private String staffName;

    @JsonProperty("staff_dob")
    private Date staffDOB;

    @JsonProperty("staff_hire_date")
    private Date staffHireDate;

    @JsonProperty("staff_position")
    private String staffPositionTitle;

    @JsonProperty("staff_shifts_ids")
    private List<Integer> staffShiftsIDs;
}
