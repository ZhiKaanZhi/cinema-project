package com.staff.staffmanagement.mapstruct.mappers;

import com.staff.staffmanagement.entity.Position;
import com.staff.staffmanagement.entity.Shifts;
import com.staff.staffmanagement.entity.Staff;
import com.staff.staffmanagement.mapstruct.dtos.ShiftsAllDto;
import com.staff.staffmanagement.mapstruct.dtos.StaffAllDto;
import com.staff.staffmanagement.mapstruct.dtos.StaffSimpleDto;
import com.staff.staffmanagement.repository.PositionRepository;
import com.staff.staffmanagement.repository.ShiftsRepository;
import com.staff.staffmanagement.repository.StaffRepository;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface StaffMapper {

    StaffAllDto staffToStaffAllDto(Staff staff);

    StaffSimpleDto staffToStaffSimpleDto(Staff staff);

    Staff staffAllDtoToStaff(StaffAllDto staffAllDto);

    Staff staffSimpleDtoToStaff(StaffSimpleDto staffSimpleDto);


    @AfterMapping
    default void afterStaffMapping (@MappingTarget StaffAllDto target, Staff source) {
        target.setStaffShiftsIDs(source.getStaffShifts().stream()
                .map(Shifts::getShiftID)
                .collect(Collectors.toList()));

        target.setStaffPositionTitle(source.getStaffPosition().getPositionTitle());
    }

    @AfterMapping
    default void dtoToEntityAfterMapping(@MappingTarget Staff target, StaffAllDto source, ShiftsRepository shiftsRepository, PositionRepository positionRepository) {

        // Handling shifts mapping with validation
        Set<Shifts> shiftsSet = source.getStaffShiftsIDs() == null ? Collections.emptySet() :
                source.getStaffShiftsIDs().stream()
                        .map(shiftsRepository::findById)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toSet());

        target.setStaffShifts(shiftsSet);

        // Synchronize both sides of the bidirectional association
        for (Shifts shift : shiftsSet) {
            shift.addStaff(target);
        }

        // Handling position mapping with validation
        Position position = positionRepository.findByPositionTitle(source.getStaffPositionTitle());
        if(position != null){
            target.setStaffPosition(position);
        } else {
            // Handle the case when the position is not found, e.g., log, throw an exception, etc.
            throw new EntityNotFoundException("Position Not Found with Title: " + source.getStaffPositionTitle().getTitle());
        }
    }

}
