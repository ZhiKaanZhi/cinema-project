package com.staff.staffmanagement.entity;

import com.staff.staffmanagement.entity.enums.StaffTitle;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@ToString
@Setter
@Getter
@NoArgsConstructor// generates a default no-arg constructor
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


    public Position(StaffTitle positionTitle, String positionDescription) {
        this.positionTitle = positionTitle;
        this.positionDescription = positionDescription;
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
