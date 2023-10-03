package com.staff.staffmanagement.repository;

import com.staff.staffmanagement.entity.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SchedulesRepository extends JpaRepository<Schedules, Integer> {
    List<Schedules> findByScheduleScreenDate(Date screenDate);
}
