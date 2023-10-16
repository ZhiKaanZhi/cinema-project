package com.staff.staffmanagement.mapstruct.mappers;

import com.staff.staffmanagement.entity.Position;
import com.staff.staffmanagement.entity.Staff;
import com.staff.staffmanagement.mapstruct.dtos.PositionAllDto;
import com.staff.staffmanagement.mapstruct.dtos.PositionSimpleDto;
import com.staff.staffmanagement.repository.StaffRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PositionMapper {

    @Mapping(source = "positionTitle", target = "positionTitle")
    @Mapping(source = "positionDescription", target = "positionDescription")
    PositionAllDto positionToPositionAllDto(Position position);


    @Mapping(source = "positionTitle", target = "positionTitle")
    @Mapping(source = "positionDescription", target = "positionDescription")
    Position positionAllDtoToPosition(PositionAllDto positionAllDto);


    @Mapping(source = "positionTitle", target = "positionTitle")
    @Mapping(source = "positionDescription", target = "positionDescription")
    PositionSimpleDto positionToPositionSimpleDto(Position position);


    @Mapping(source = "positionTitle", target = "positionTitle")
    @Mapping(source = "positionDescription", target = "positionDescription")
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
