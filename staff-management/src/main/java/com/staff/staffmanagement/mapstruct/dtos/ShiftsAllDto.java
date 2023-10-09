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


    public Integer getShiftID() {
        return shiftID;
    }

    public void setShiftID(Integer shiftID) {
        this.shiftID = shiftID;
    }

    public Time getShiftStartTime() {
        return shiftStartTime;
    }

    public void setShiftStartTime(Time shiftStartTime) {
        this.shiftStartTime = shiftStartTime;
    }

    public Time getShiftEndTime() {
        return shiftEndTime;
    }

    public void setShiftEndTime(Time shiftEndTime) {
        this.shiftEndTime = shiftEndTime;
    }

    public Date getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(Date shiftDate) {
        this.shiftDate = shiftDate;
    }

    public List<Integer> getShiftStaffIDs() {
        return shiftStaffIDs;
    }

    public void setShiftStaffIDs(List<Integer> shiftStaffIDs) {
        this.shiftStaffIDs = shiftStaffIDs;
    }
}
