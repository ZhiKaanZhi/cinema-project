package com.staff.staffmanagement.service;

import com.staff.staffmanagement.entity.Position;
import com.staff.staffmanagement.entity.Shifts;
import com.staff.staffmanagement.entity.Staff;
import com.staff.staffmanagement.entity.enums.StaffTitle;
import com.staff.staffmanagement.mapstruct.dtos.StaffAllDto;
import com.staff.staffmanagement.mapstruct.dtos.StaffSimpleDto;
import com.staff.staffmanagement.mapstruct.mappers.StaffMapper;
import com.staff.staffmanagement.repository.PositionRepository;
import com.staff.staffmanagement.repository.ShiftsRepository;
import com.staff.staffmanagement.repository.StaffRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private ShiftsRepository shiftRepository;

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private ShiftsRepository shiftsRepository;

    /**
     * Retrieve all available staff entries.
     *
     * @return a List of all Staff entities.
     */
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    /**
     * Retrieve detailed staff information (including position and shifts) by staff ID.
     *
     * @param id ID of the staff to retrieve detailed information.
     * @return StaffAllDto containing detailed staff information including shifts and position title.
     */
    public StaffAllDto getStaffAllDtoById(int id) {
        Staff staff = staffRepository.findById(id).orElse(null);

        Position position = positionRepository.findById(
                Objects.requireNonNull(Optional.ofNullable(staff)
                        .map(Staff::getStaffPosition)
                        .map(Position::getPositionID)
                        .orElse(null))
        ).orElse(null);

        List<Shifts> shifts = shiftRepository.findByShiftStaff_StaffID(
                Optional.of(staff).map(Staff::getStaffID).orElse(null)
        );

        StaffAllDto staffAllDto = staffMapper.staffToStaffAllDto(staff);

        if(position != null) {
            staffAllDto.setStaffPositionTitle(position.getPositionTitle());
        } else {
            staffAllDto.setStaffPositionTitle(StaffTitle.DEFAULT_EMPLOYEE);  // Replace "Default_Title" with your default title
        }

        List<Integer> shiftIds = shifts.stream().map(Shifts::getShiftID).collect(Collectors.toList());
        staffAllDto.setStaffShiftsIDs(shiftIds);

        return staffAllDto;
    }

    /**
     * Retrieve basic staff information and position title by staff ID.
     *
     * @param id ID of the staff to retrieve basic information.
     * @return StaffSimpleDto containing basic staff information and position title.
     */
    public StaffSimpleDto getStaffSimpleDtoById(int id) {
        Staff staff = staffRepository.findById(id).orElse(null);

        Position position = positionRepository.findById(
                Objects.requireNonNull(Optional.ofNullable(staff)
                        .map(Staff::getStaffPosition)
                        .map(Position::getPositionID)
                        .orElse(null))
        ).orElse(null);

        StaffSimpleDto staffSimpleDto = staffMapper.staffToStaffSimpleDto(staff);

        if(position != null) {
            staffSimpleDto.setStaffPositionTitle(position.getPositionTitle());
        } else {
            staffSimpleDto.setStaffPositionTitle(StaffTitle.DEFAULT_EMPLOYEE);  // Replace "Default_Title" with your default title
        }

        return staffSimpleDto;
    }

    /**
     * Create a new staff entry using the provided StaffAllDto and return the detailed information.
     *
     * @param staffAllDto Detailed staff information for creating a new staff entry.
     * @return StaffAllDto containing detailed information of the created staff.
     */
    @Transactional
    public StaffAllDto createStaffAllDto(StaffAllDto staffAllDto) {
        Position position = positionRepository.findByPositionTitle(staffAllDto.getStaffPositionTitle());

        // If the position is not found, you may want to create a new position (depending on business rules).
        if(position == null) {
            position = new Position();
            position.setPositionTitle(staffAllDto.getStaffPositionTitle());
            position = positionRepository.save(position);
        }

        Staff staff = staffMapper.staffAllDtoToStaff(staffAllDto);
        staff.setStaffPosition(position);

        // If the staffAllDto has associated shift IDs, fetch those shifts and manage relationships.
        if (staffAllDto.getStaffShiftsIDs() != null && !staffAllDto.getStaffShiftsIDs().isEmpty()) {
            Set<Shifts> shifts = new HashSet<>(shiftsRepository.findAllById(staffAllDto.getStaffShiftsIDs()));

            staff.getStaffShifts().clear();
            staff.getStaffShifts().addAll(shifts);

            for (Shifts shift : shifts) {
                shift.getShiftStaff().add(staff);
            }
        }

        staff = staffRepository.saveAndFlush(staff);

        return staffMapper.staffToStaffAllDto(staff);
    }

    /**
     * Create a new staff entry using the provided StaffSimpleDto and return the basic information.
     *
     * @param staffSimpleDto Basic staff information for creating a new staff entry.
     * @return StaffSimpleDto containing basic information of the created staff.
     */
    @Transactional
    public StaffSimpleDto createStaffSimpleDto(StaffSimpleDto staffSimpleDto) {
        Position position = positionRepository.findByPositionTitle(staffSimpleDto.getStaffPositionTitle());

        Staff staff = staffMapper.staffSimpleDtoToStaff(staffSimpleDto);
        staff.setStaffPosition(position);
        staff = staffRepository.saveAndFlush(staff);

        return staffMapper.staffToStaffSimpleDto(staff);
    }

    @Transactional
    public StaffAllDto updateStaff(StaffAllDto staffAllDto) {
        if (staffAllDto.getStaffID() == 0) {
            throw new IllegalArgumentException("Staff ID is required for update");
        }

        // Retrieve existing staff entity
        Staff existingStaff = staffRepository.findById(staffAllDto.getStaffID())
                .orElseThrow(() -> new EntityNotFoundException("Staff not found with ID: " + staffAllDto.getStaffID()));

        // Update fields from DTO to the existing entity
        existingStaff.setStaffName(staffAllDto.getStaffName());
        existingStaff.setStaffDOB(staffAllDto.getStaffDOB());
        existingStaff.setStaffHireDate(staffAllDto.getStaffHireDate());

        // Update the position (similar logic as in createStaffAllDto)
        Position position = positionRepository.findByPositionTitle(staffAllDto.getStaffPositionTitle());
        if (position == null) {
            position = new Position();
            position.setPositionTitle(staffAllDto.getStaffPositionTitle());
            position = positionRepository.save(position);
        }
        existingStaff.setStaffPosition(position);

        // Update shifts
        if (staffAllDto.getStaffShiftsIDs() != null && !staffAllDto.getStaffShiftsIDs().isEmpty()) {
            Set<Shifts> shifts = new HashSet<>(shiftsRepository.findAllById(staffAllDto.getStaffShiftsIDs()));
            existingStaff.getStaffShifts().clear();
            existingStaff.getStaffShifts().addAll(shifts);
            for (Shifts shift : shifts) {
                shift.getShiftStaff().add(existingStaff);
            }
        }

        // Save updated staff entity
        Staff updatedStaff = staffRepository.saveAndFlush(existingStaff);

        // Convert updated entity to DTO and return
        return staffMapper.staffToStaffAllDto(updatedStaff);
    }

    /**
     * Delete a staff entry by ID.
     *
     * @param id ID of the staff entry to delete.
     */
    @Transactional
    public void deleteStaff(Integer id) {
        staffRepository.deleteById(id);
    }



    @Transactional
    public void assignShiftToStaff(Integer staffId, Integer shiftId) {
        Staff staff = staffRepository.findById(staffId).orElseThrow(
                () -> new EntityNotFoundException("Staff not found")
        );
        Shifts shift = shiftRepository.findById(shiftId).orElseThrow(
                () -> new EntityNotFoundException("Shift not found")
        );
        staff.addShift(shift);
    }
}
