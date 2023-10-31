package com.staff.staffmanagement.entity;


import com.staff.staffmanagement.entity.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor// generates a default no-arg constructor
@Table(name = "staff")
public class Staff implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private int staffID;

    @Column(name = "staff_name")
    @NotNull(message = "Staff name cannot be null")
    @Size(min = 2, max = 50, message = "Staff name must be between 2 and 50 characters")
    private String staffName;

    @Column(name = "staff_username")
    private String staffUsername;

    @Column(name = "staff_email")
    @Email(message = "Please provide a valid email address")
    private String staffEmail;

    @Column(name = "staff_password", nullable = false)
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String staffPassword;

    @Column(name = "staff_dob")
    private Date staffDOB;

    @Column(name = "staff_hire_date")
    private Date staffHireDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "staff_role")
    private Role staffRole;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "position_id")
    private Position staffPosition;

    @ToString.Exclude
    @ManyToMany(cascade = { CascadeType.PERSIST })
    @JoinTable(
            name = "staff_shifts",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "shift_id")
    )
    private Set<Shifts> staffShifts = new HashSet<>();

    public Staff(String staffName, String staffUsername, String staffEmail, String staffPassword, Date staffDOB, Date staffHireDate, Role staffRole) {
        this.staffName = staffName;
        this.staffUsername = staffUsername;
        this.staffEmail = staffEmail;
        this.staffPassword = staffPassword;
        this.staffDOB = staffDOB;
        this.staffHireDate = staffHireDate;
        this.staffRole = staffRole;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(staffPosition.getPositionTitle().getTitle()));
    }

    @Override
    public String getPassword() {
        return staffPassword;
    }

    @Override
    public String getUsername() {
        return staffUsername;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
