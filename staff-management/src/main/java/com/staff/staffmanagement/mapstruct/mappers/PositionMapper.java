package com.staff.staffmanagement.mapstruct.mappers;

import com.staff.staffmanagement.entity.Position;
import com.staff.staffmanagement.mapstruct.dtos.PositionAllDto;
import com.staff.staffmanagement.mapstruct.dtos.PositionSimpleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PositionMapper {
    PositionAllDto positionToPositionAllDto(Position position);
    Position positionAllDtoToPosition(PositionAllDto positionAllDto);
    PositionSimpleDto positionToPositionSimpleDto(Position position);
    Position positionSimpleDtoToPosition(PositionSimpleDto positionSimpleDto);
}
