package com.staff.staffmanagement.mapstruct.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.staff.staffmanagement.entity.enums.StaffTitle;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
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

    @JsonProperty("staff_username")
    private String staffUsername;

    @JsonProperty("staff_email")
    private String staffEmail;

    @JsonProperty("staff_password")
    private String staffPassword;

    @JsonProperty("staff_dob")
    private Date staffDOB;

    @JsonProperty("staff_hire_date")
    private Date staffHireDate;

    @JsonProperty("staff_role")
    private String staffRole;

    @JsonProperty("staff_position")
    private StaffTitle staffPositionTitle;

    @JsonProperty("staff_shifts_ids")
    private List<Integer> staffShiftsIDs;
}
