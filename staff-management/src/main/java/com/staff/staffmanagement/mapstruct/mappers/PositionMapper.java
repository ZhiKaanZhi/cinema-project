package com.staff.staffmanagement.mapstruct.mappers;

import com.staff.staffmanagement.entity.Position;
import com.staff.staffmanagement.entity.Staff;
import com.staff.staffmanagement.mapstruct.dtos.PositionAllDto;
import com.staff.staffmanagement.mapstruct.dtos.PositionSimpleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PositionMapper {

    @Mapping(source = "positionStaffMembers", target = "positionStaffMemberIDs", qualifiedByName = "staffSetToIdList")
    PositionAllDto positionToPositionAllDto(Position position);

    @Mapping(target = "positionStaffMembers", ignore = true)  // We ignore this mapping because it requires database interaction
    Position positionAllDtoToPosition(PositionAllDto positionAllDto);

    PositionSimpleDto positionToPositionSimpleDto(Position position);

    Position positionSimpleDtoToPosition(PositionSimpleDto positionSimpleDto);

    @Named("staffSetToIdList")
    default List<Integer> staffSetToIdList(Set<Staff> staff) {
        return staff == null ? null : staff.stream().map(Staff::getStaffID).collect(Collectors.toList());
    }
}
