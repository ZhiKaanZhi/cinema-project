package com.staff.staffmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor  // generates a default no-arg constructor
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

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff shiftStaff;

    public Shifts(Time shiftStartTime, Time shiftEndTime, Date shiftDate, Staff shiftStaff) {
        this.shiftStartTime = shiftStartTime;
        this.shiftEndTime = shiftEndTime;
        this.shiftDate = shiftDate;
        this.shiftStaff = shiftStaff;
    }

    public Shifts(Time shiftStartTime, Time shiftEndTime, Date shiftDate) {
        this.shiftStartTime = shiftStartTime;
        this.shiftEndTime = shiftEndTime;
        this.shiftDate = shiftDate;
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
