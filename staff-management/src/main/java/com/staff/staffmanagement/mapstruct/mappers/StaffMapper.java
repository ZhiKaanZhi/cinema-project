package com.staff.staffmanagement.mapstruct.mappers;

import com.staff.staffmanagement.entity.Position;
import com.staff.staffmanagement.entity.Shifts;
import com.staff.staffmanagement.entity.Staff;
import com.staff.staffmanagement.mapstruct.dtos.StaffAllDto;
import com.staff.staffmanagement.mapstruct.dtos.StaffSimpleDto;
import com.staff.staffmanagement.repository.PositionRepository;
import com.staff.staffmanagement.repository.ShiftsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface StaffMapper {

    @Mapping(target="staffID", source="staffID")
    @Mapping(target="staffName", source="staffName")
    @Mapping(target="staffUsername", source="staffUsername")
    @Mapping(target="staffEmail", source="staffEmail")
    @Mapping(target="staffPassword", source="staffPassword")
    @Mapping(target="staffDOB", source="staffDOB")
    @Mapping(target="staffHireDate", source="staffHireDate")
    StaffAllDto staffToStaffAllDto(Staff staff);

    @Mapping(target="staffID", source="staffID")
    @Mapping(target="staffName", source="staffName")
    @Mapping(target="staffUsername", source="staffUsername")
    @Mapping(target="staffEmail", source="staffEmail")
    @Mapping(target="staffPassword", source="staffPassword")
    @Mapping(target="staffDOB", source="staffDOB")
    @Mapping(target="staffHireDate", source="staffHireDate")
    StaffSimpleDto staffToStaffSimpleDto(Staff staff);

    @Mapping(target="staffID", source="staffID")
    @Mapping(target="staffName", source="staffName")
    @Mapping(target="staffUsername", source="staffUsername")
    @Mapping(target="staffEmail", source="staffEmail")
    @Mapping(target="staffPassword", source="staffPassword")
    @Mapping(target="staffDOB", source="staffDOB")
    @Mapping(target="staffHireDate", source="staffHireDate")
    Staff staffAllDtoToStaff(StaffAllDto staffAllDto);

    @Mapping(target="staffID", source="staffID")
    @Mapping(target="staffName", source="staffName")
    @Mapping(target="staffUsername", source="staffUsername")
    @Mapping(target="staffEmail", source="staffEmail")
    @Mapping(target="staffPassword", source="staffPassword")
    @Mapping(target="staffDOB", source="staffDOB")
    @Mapping(target="staffHireDate", source="staffHireDate")
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
        System.out.println("Staff after mapping: "+ target);
    }

}
