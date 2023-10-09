package com.staff.staffmanagement.entity;

import com.staff.staffmanagement.entity.enums.StaffTitle;
import jakarta.persistence.*;
import lombok.*;

import javax.swing.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private int positionID;

    @Enumerated(EnumType.STRING)
    @Column(name = "position_title")
    private StaffTitle positionTitle;

    @Column(name = "position_description")
    private String positionDescription;

    @OneToMany(mappedBy = "position")
    private Set<Staff> positionStaffMembers = new HashSet<>();


    public Position() {
    }

    public Position(StaffTitle positionTitle, String positionDescription) {
        this.positionTitle = positionTitle;
        this.positionDescription = positionDescription;
    }

    public int getPositionID() {
        return positionID;
    }

    public void setPositionID(int positionID) {
        this.positionID = positionID;
    }

    public StaffTitle getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(StaffTitle positionTitle) {
        this.positionTitle = positionTitle;
    }

    public String getPositionDescription() {
        return positionDescription;
    }

    public void setPositionDescription(String positionDescription) {
        this.positionDescription = positionDescription;
    }

    public Set<Staff> getPositionStaffMembers() {
        return positionStaffMembers;
    }

    public void setPositionStaffMembers(Set<Staff> positionStaffMembers) {
        this.positionStaffMembers = positionStaffMembers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(positionID, position.positionID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionID);
    }
}
