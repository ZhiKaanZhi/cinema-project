package com.staff.staffmanagement.repository;

import com.staff.staffmanagement.entity.Position;
import com.staff.staffmanagement.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {

    Position findByTitle(String staffPositionTitle);
}
