package com.staff.staffmanagement.service;

import com.staff.staffmanagement.entity.Position;
import com.staff.staffmanagement.entity.Staff;
import com.staff.staffmanagement.entity.enums.StaffTitle;
import com.staff.staffmanagement.mapstruct.dtos.PositionAllDto;
import com.staff.staffmanagement.mapstruct.dtos.PositionSimpleDto;
import com.staff.staffmanagement.mapstruct.mappers.PositionMapper;
import com.staff.staffmanagement.repository.PositionRepository;
import com.staff.staffmanagement.repository.StaffRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private PositionMapper positionMapper;

    /**
     * Retrieve all positions in a simplified format.
     *
     * @return a List of PositionSimpleDto representing all available positions.
     */
    public List<PositionSimpleDto> getAllPositionsSimple() {
        return positionRepository.findAll().stream()
                .map(positionMapper::positionToPositionSimpleDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve all positions in a detailed format.
     *
     * @return a List of PositionAllDto representing all available positions.
     */
    public List<PositionAllDto> getAllPositionsDetailed() {
        return positionRepository.findAll().stream()
                .map(positionMapper::positionToPositionAllDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieve the details of a specific position.
     *
     * @param id the ID of the position.
     * @return a PositionAllDto representing the position details, or null if not found.
     */
    public PositionAllDto getPositionById(int id) {
        return positionRepository.findById(id)
                .map(positionMapper::positionToPositionAllDto)
                .orElseThrow(() -> new EntityNotFoundException("Position not found: " + id));
    }

    /**
     * Save a new position or update an existing one.
     *
     * @param positionAllDto the position data to be saved.
     * @return the saved PositionAllDto.
     */
    @Transactional
    public PositionAllDto savePosition(PositionAllDto positionAllDto) {
        Position position = positionRepository.saveAndFlush(positionMapper.positionAllDtoToPosition(positionAllDto));
        return positionMapper.positionToPositionAllDto(position);
    }

    /**
     * Delete a position.
     *
     * @param id the ID of the position to be deleted.
     */
    @Transactional
    public void deletePosition(int id) {
        // Ensure the position exists before attempting to delete it
        Position position = positionRepository.findById(id)
                .orElse(null);

        // Dissociate all staff members from the position being deleted
        if(position != null) {

            Set<Staff> staffSet = position.getPositionStaffMembers();
            for (Staff staff: staffSet) {
                staff.setStaffPosition(null);
                staffRepository.saveAndFlush(staff);
            }


            /*position.getPositionStaffMembers().forEach(staff -> {
                staff.setStaffPosition(null);
                staffRepository.saveAndFlush(staff);
            });*/
            // Delete the position
            positionRepository.delete(position);
        }
    }

    /**
     * Retrieve a simplified view of a position by title.
     *
     * @param title the title of the position.
     * @return a PositionSimpleDto representing the position, or null if not found.
     */
    public PositionSimpleDto getPositionSimpleByTitle(String title) {
        Position position = positionRepository.findByPositionTitle(title);
        return position != null ? positionMapper.positionToPositionSimpleDto(position) : null;
    }
}
