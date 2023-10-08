package com.staff.staffmanagement.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor  // generates a default no-arg constructor
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private int staffID;

    @Column(name = "staff_name")
    @NotNull(message = "Staff name cannot be null")
    @Size(min = 2, max = 50, message = "Staff name must be between 2 and 50 characters")
    private String staffName;

    @Column(name = "staff_dob")
    private Date staffDOB;

    @Column(name = "staff_hire_date")
    private Date staffHireDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "position_id")
    private Position staffPosition;

    @ManyToMany
    @JoinTable(
            name = "staff_shift",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "shift_id")
    )
    private Set<Shifts> staffShifts = new HashSet<>();

    public Staff(String staffName, Date staffDOB, Date staffHireDate, Position staffPosition, Set<Shifts> staffShifts) {
        this.staffName = staffName;
        this.staffDOB = staffDOB;
        this.staffHireDate = staffHireDate;
        this.staffPosition = staffPosition;
        this.staffShifts = staffShifts;
    }

    public Staff(String staffName, Date staffDOB, Date staffHireDate) {
        this.staffName = staffName;
        this.staffDOB = staffDOB;
        this.staffHireDate = staffHireDate;
    }

    public Staff(String staffName, Date staffDOB, Date staffHireDate, Position staffPosition) {
        this.staffName = staffName;
        this.staffDOB = staffDOB;
        this.staffHireDate = staffHireDate;
        this.staffPosition = staffPosition;
    }

    public Staff(String staffName, Date staffDOB, Date staffHireDate, Set<Shifts> staffShifts) {
        this.staffName = staffName;
        this.staffDOB = staffDOB;
        this.staffHireDate = staffHireDate;
        this.staffShifts = staffShifts;
    }


    public void addShift(Shifts shift) {
        staffShifts.add(shift);
        shift.getShiftStaff().add(this);
    }

    public void removeShift(Shifts shift) {
        staffShifts.remove(shift);
        shift.getShiftStaff().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return Objects.equals(staffID, staff.staffID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staffID);
    }
}
