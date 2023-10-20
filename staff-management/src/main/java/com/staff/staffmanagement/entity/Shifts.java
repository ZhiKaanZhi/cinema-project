package com.staff.staffmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@ToString
@Setter
@Getter
@NoArgsConstructor// generates a default no-arg constructor
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

    @ToString.Exclude
    @ManyToMany(mappedBy = "staffShifts", cascade = CascadeType.PERSIST)
    private Set<Staff> shiftStaff = new HashSet<>();


    public Shifts(Time shiftStartTime, Time shiftEndTime, Date shiftDate) {
        this.shiftStartTime = shiftStartTime;
        this.shiftEndTime = shiftEndTime;
        this.shiftDate = shiftDate;
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
