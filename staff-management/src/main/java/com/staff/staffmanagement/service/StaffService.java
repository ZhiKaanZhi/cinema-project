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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Retrieve all available staff entries.
     *
     * @return a List of all Staff entities.
     */
    public List<StaffAllDto> getAllStaff() {
        List<Staff> staffList = staffRepository.findAll();
        List<StaffAllDto> dtoList = new ArrayList<>();

        for (Staff staff: staffList) {
            dtoList.add(staffMapper.staffToStaffAllDto(staff));
        }
        return dtoList;
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
    public StaffAllDto registerStaffAllDto(StaffAllDto staffAllDto) {
        Position position = positionRepository.findByPositionTitle(staffAllDto.getStaffPositionTitle());

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

        // Hash the password
        staff.setStaffPassword(passwordEncoder.encode(staff.getStaffPassword()));

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
    public StaffSimpleDto registerStaffSimpleDto(StaffSimpleDto staffSimpleDto) {
        Position position = positionRepository.findByPositionTitle(staffSimpleDto.getStaffPositionTitle());

        if(position == null) {
            position = new Position();
            position.setPositionTitle(staffSimpleDto.getStaffPositionTitle());
            position = positionRepository.save(position);
        }

        Staff staff = staffMapper.staffSimpleDtoToStaff(staffSimpleDto);
        staff.setStaffPosition(position);

        // Hash the password
        staff.setStaffPassword(passwordEncoder.encode(staff.getStaffPassword()));

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


    /**
     * Authenticates a staff member using their email and raw password.
     *
     * @param email the email address of the staff member.
     * @param rawPassword the plaintext password entered by the staff member.
     * @return true if authentication was successful, false otherwise.
     */
    public boolean authenticateStaff(String email, String rawPassword) {
        // Retrieve the staff member by their email address
        Staff staff = staffRepository.findByStaffEmail(email);

        // If no staff member was found with the given email or the password is null, authentication fails
        if (staff == null || staff.getStaffPassword() == null) {
            return false;
        }

        // Compare the plaintext password with the hashed version stored in the database
        if (passwordEncoder.matches(rawPassword, staff.getStaffPassword())) {
            return true; // Authenticated successfully
        } else {
            return false; // Authentication failed
        }
    }



    public StaffSimpleDto getStaffSimpleDtoByStaffUsername(String username) {
        Staff staff = staffRepository.findByStaffUsername(username);

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
}
