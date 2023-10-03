package com.staff.staffmanagement.repository;

import com.staff.staffmanagement.entity.Shifts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftsRepository extends JpaRepository<Shifts, Integer> {
}
