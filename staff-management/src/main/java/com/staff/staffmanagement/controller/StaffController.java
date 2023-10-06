package com.staff.staffmanagement.controller;

import com.staff.staffmanagement.entity.Staff;
import com.staff.staffmanagement.exception.ResourceNotFoundException;
import com.staff.staffmanagement.mapstruct.dtos.StaffAllDto;
import com.staff.staffmanagement.mapstruct.dtos.StaffSimpleDto;
import com.staff.staffmanagement.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    /**
     * Retrieve all available staff entries.
     *
     * @return List of all staff entries.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Staff>> getAllStaff() {
        return new ResponseEntity<>(staffService.getAllStaff(), HttpStatus.OK);
    }

    /**
     * Retrieve detailed staff information by ID.
     *
     * @param id ID of the staff to retrieve.
     * @return Detailed information of the requested staff.
     */
    @GetMapping("/details/{id}")
    public ResponseEntity<StaffAllDto> getStaffAllDetailsById(@PathVariable Integer id) {
        StaffAllDto staff = staffService.getStaffAllDtoById(id);
        if(staff == null) {
            throw new ResourceNotFoundException("Staff not found with id: " + id);
        }
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }

    /**
     * Retrieve basic staff information by ID.
     *
     * @param id ID of the staff to retrieve.
     * @return Basic information of the requested staff.
     */
    @GetMapping("/basic/{id}")
    public ResponseEntity<StaffSimpleDto> getStaffSimpleById(@PathVariable Integer id) {
        StaffSimpleDto staff = staffService.getStaffSimpleDtoById(id);
        if(staff == null) {
            throw new ResourceNotFoundException("Staff not found with id: " + id);
        }
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }

    /**
     * Create a new staff entry with detailed information.
     *
     * @param staffAllDto Detailed staff information for the new staff entry.
     * @return Detailed information of the created staff entry.
     */
    @PostMapping("/create-details")
    public ResponseEntity<StaffAllDto> createStaffAllDetails(@RequestBody StaffAllDto staffAllDto) {
        return new ResponseEntity<>(staffService.createStaffAllDto(staffAllDto), HttpStatus.CREATED);
    }

    /**
     * Create a new staff entry with basic information.
     *
     * @param staffSimpleDto Basic staff information for the new staff entry.
     * @return Basic information of the created staff entry.
     */
    @PostMapping("/create-basic")
    public ResponseEntity<StaffSimpleDto> createStaffSimple(@RequestBody StaffSimpleDto staffSimpleDto) {
        return new ResponseEntity<>(staffService.createStaffSimpleDto(staffSimpleDto), HttpStatus.CREATED);
    }

    /**
     * Delete a staff entry by ID.
     *
     * @param id ID of the staff entry to delete.
     * @return HTTP status indicating the result of the operation.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Integer id) {
        staffService.deleteStaff(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
