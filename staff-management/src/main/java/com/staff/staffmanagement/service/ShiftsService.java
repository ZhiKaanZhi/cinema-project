package com.staff.staffmanagement.service;


import com.staff.staffmanagement.entity.Shifts;
import com.staff.staffmanagement.entity.Staff;
import com.staff.staffmanagement.exception.InvalidInputException;
import com.staff.staffmanagement.mapstruct.dtos.ShiftsAllDto;
import com.staff.staffmanagement.mapstruct.dtos.ShiftsSimpleDto;
import com.staff.staffmanagement.mapstruct.mappers.ShiftsMapper;
import com.staff.staffmanagement.repository.ShiftsRepository;
import com.staff.staffmanagement.repository.StaffRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShiftsService {

    @Autowired
    private ShiftsRepository shiftsRepository;

    @Autowired
    private ShiftsMapper shiftsMapper;

    @Autowired
    private StaffRepository staffRepository;

    /**
     * Retrieve all shift details.
     *
     * @return a list of all shift details as {@link ShiftsAllDto} instances.
     */
    public List<ShiftsAllDto> getAllShiftsDetails() {
        return shiftsRepository.findAll().stream()
                .map(shiftsMapper::shiftsToShiftsAllDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve all shifts with simplified details.
     *
     * @return a list of all simple shift details as {@link ShiftsSimpleDto} instances.
     */
    public List<ShiftsSimpleDto> getAllShiftsSimple() {
        return shiftsRepository.findAll().stream()
                .map(shiftsMapper::shiftsToShiftsSimpleDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve simple details of a specific shift by ID.
     *
     * @param id the ID of the desired shift.
     * @return simplified shift details as a {@link ShiftsSimpleDto}.
     */
    public ShiftsSimpleDto getSimpleShiftById(Integer id) {
        return shiftsRepository.findById(id)
                .map(shiftsMapper::shiftsToShiftsSimpleDto)
                .orElse(null);
    }

    /**
     * Retrieve detailed information of a specific shift by ID.
     *
     * @param id the ID of the desired shift.
     * @return shift details as a {@link ShiftsAllDto}.
     */
    public ShiftsAllDto getAllShiftDetailById(Integer id) {
        return shiftsRepository.findById(id)
                .map(shiftsMapper::shiftsToShiftsAllDto)
                .orElse(null);
    }

    /**
     * Save a new shift or update an existing one.
     *
     * @param shiftsAllDto data transfer object containing shift information.
     * @return saved shift details as a {@link ShiftsAllDto}.
     */
    @Transactional
    public ShiftsAllDto saveShift(ShiftsAllDto shiftsAllDto) {
        // Validate input - ensuring staff IDs are provided
        if(shiftsAllDto.getShiftStaffIDs() == null || shiftsAllDto.getShiftStaffIDs().isEmpty()) {
            throw new InvalidInputException("Staff IDs must be provided.");
        }

        // Retrieve Staff entities from the provided IDs
        List<Staff> staffList = staffRepository.findAllById(shiftsAllDto.getShiftStaffIDs());
        Set<Staff> staffMembers = new HashSet<>(staffList);

        // Validate - ensuring all staff entities are found
        if(staffMembers.size() != shiftsAllDto.getShiftStaffIDs().size()) {
            throw new EntityNotFoundException("One or more Staff entities not found.");
        }

        // Map DTO to entity
        Shifts shift = shiftsMapper.shiftsAllDtoToShifts(shiftsAllDto);

        // Associate retrieved staff entities with the shift
        shift.setShiftStaff(staffMembers);

        // If bi-directional, update the other side of the relationship
        staffMembers.forEach(staff -> staff.getStaffShifts().add(shift));

        // Save the shift along with its associations
        shiftsRepository.saveAndFlush(shift);

        // Map entity back to DTO for the response
        return shiftsMapper.shiftsToShiftsAllDto(shift);
    }

    /**
     * Delete a shift by ID.
     *
     * @param id the ID of the shift to be deleted.
     */
    public void deleteShift(Integer id) {
        shiftsRepository.deleteById(id);
    }

    /**
     * Retrieve shifts occurring on a specific date.
     *
     * @param date the date to filter shifts by.
     * @return a list of shift details for the specified date.
     */
    public List<ShiftsAllDto> findShiftsByDate(Date date) {
        return mapShiftsToShiftsAllDto(shiftsRepository.findByShiftDate(date));
    }

    /**
     * Retrieve shifts starting at a specific time.
     *
     * @param time the starting time to filter shifts by.
     * @return a list of shift details starting at the specified time.
     */
    public List<ShiftsAllDto> findShiftsByStartTime(Time time) {
        return mapShiftsToShiftsAllDto(shiftsRepository.findByShiftStartTime(time));
    }

    /**
     * Retrieve shifts ending at a specific time.
     *
     * @param time the ending time to filter shifts by.
     * @return a list of shift details ending at the specified time.
     */
    public List<ShiftsAllDto> findShiftsByEndTime(Time time) {
        return mapShiftsToShiftsAllDto(shiftsRepository.findByShiftEndTime(time));
    }

    /**
     * Map a list of Shifts entities to a list of ShiftsAllDto objects.
     *
     * @param shifts the list of {@link Shifts} entities to map.
     * @return a list of mapped {@link ShiftsAllDto} objects.
     */
    private List<ShiftsAllDto> mapShiftsToShiftsAllDto(List<Shifts> shifts) {
        return shifts.stream()
                .map(shiftsMapper::shiftsToShiftsAllDto)
                .collect(Collectors.toList());
    }
}
