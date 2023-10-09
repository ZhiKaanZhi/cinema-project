package com.staff.staffmanagement.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Table(name = "shifts")
public class Shifts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shift_id")
    private Integer shiftID;

    @Column(name = "shift_start_time")
    private Time shiftStartTime;

    @Column(name = "shift_end_time")
    private Time shiftEndTime;

    @Column(name = "shift_date")
    private Date shiftDate;

    @ManyToMany(mappedBy = "staffShifts")
    private Set<Staff> shiftStaff = new HashSet<>();


    public Shifts() {
    }

    public Shifts(Time shiftStartTime, Time shiftEndTime, Date shiftDate) {
        this.shiftStartTime = shiftStartTime;
        this.shiftEndTime = shiftEndTime;
        this.shiftDate = shiftDate;
    }

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

    public Set<Staff> getShiftStaff() {
        return shiftStaff;
    }

    public void setShiftStaff(Set<Staff> shiftStaff) {
        this.shiftStaff = shiftStaff;
    }

    public void addStaff(Staff staff) {
        shiftStaff.add(staff);
        staff.getStaffShifts().add(this);
    }

    public void removeStaff(Staff staff) {
        shiftStaff.remove(staff);
        staff.getStaffShifts().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shifts shifts = (Shifts) o;
        return Objects.equals(shiftID, shifts.shiftID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shiftID);
    }
}
