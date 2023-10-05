package com.staff.staffmanagement.repository;

import com.staff.staffmanagement.entity.Shifts;
import com.staff.staffmanagement.mapstruct.dtos.ShiftsAllDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Repository
public interface ShiftsRepository extends JpaRepository<Shifts, Integer> {

    //List<Shifts> findByShiftID(Integer shiftID);

    List<Shifts> findByShiftStaff_StaffID(int staffID);

    List<Shifts> findByShiftDate(Date date);

    List<Shifts> findByShiftStartTime(Time time);

    List<Shifts> findByShiftEndTime(Time time);
}
