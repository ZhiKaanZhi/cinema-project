package com.staff.staffmanagement.mapstruct.mappers;

import com.staff.staffmanagement.entity.Position;
import com.staff.staffmanagement.entity.Staff;
import com.staff.staffmanagement.mapstruct.dtos.PositionAllDto;
import com.staff.staffmanagement.mapstruct.dtos.PositionSimpleDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PositionMapper {
    PositionAllDto positionToPositionAllDto(Position position);
    Position positionAllDtoToPosition(PositionAllDto positionAllDto);
    PositionSimpleDto positionToPositionSimpleDto(Position position);
    Position positionSimpleDtoToPosition(PositionSimpleDto positionSimpleDto);

    default List<Integer> staffSetToIdList(Set<Staff> staff) {
        if (staff == null) return null;
        return staff.stream().map(Staff::getStaffID).collect(Collectors.toList());
    }

}
