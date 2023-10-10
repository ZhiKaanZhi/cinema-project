package com.staff.staffmanagement.mapstruct.mappers;

import com.staff.staffmanagement.entity.Position;
import com.staff.staffmanagement.entity.Staff;
import com.staff.staffmanagement.mapstruct.dtos.PositionAllDto;
import com.staff.staffmanagement.mapstruct.dtos.PositionSimpleDto;
import com.staff.staffmanagement.repository.StaffRepository;
import org.mapstruct.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PositionMapper {

    PositionAllDto positionToPositionAllDto(Position position);

    Position positionAllDtoToPosition(PositionAllDto positionAllDto);

    PositionSimpleDto positionToPositionSimpleDto(Position position);

    Position positionSimpleDtoToPosition(PositionSimpleDto positionSimpleDto);


    @AfterMapping
    default void afterPositionMapping (@MappingTarget PositionAllDto target, Position source) {
        target.setPositionStaffMemberIDs(source.getPositionStaffMembers().stream()
                .map(Staff::getStaffID)
                .collect(Collectors.toList()));
    }

    @AfterMapping
    default void dtoToEntityAfterMapping(@MappingTarget Position target, PositionAllDto source, StaffRepository staffRepository) {
        Set<Staff> staffSet = source.getPositionStaffMemberIDs().stream()
                .map(staffRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        target.setPositionStaffMembers(staffSet);
    }

}
