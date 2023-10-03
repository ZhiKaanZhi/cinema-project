package com.staff.staffmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.swing.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor  // generates a default no-arg constructor
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private int positionID;

    @Column(name = "position_title")
    private String positionTitle;

    @Column(name = "position_description")
    private String positionDescription;

    @OneToMany(mappedBy = "position")
    private Set<Staff> positionStaffMembers = new HashSet<>();

    public Position(String title) {
    }

    public Position(String positionTitle, String positionDescription, Set<Staff> positionStaffMembers) {
        this.positionTitle = positionTitle;
        this.positionDescription = positionDescription;
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
