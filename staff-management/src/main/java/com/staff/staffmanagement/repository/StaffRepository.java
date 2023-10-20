package com.staff.staffmanagement.repository;

import com.staff.staffmanagement.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

    List<Staff> findByStaffName(String staffName);

    Staff findByStaffEmail(String email);
}
