package com.staff.staffmanagement.mapstruct.mappers;

import com.staff.staffmanagement.entity.Shifts;
import com.staff.staffmanagement.mapstruct.dtos.ShiftsAllDto;
import com.staff.staffmanagement.mapstruct.dtos.ShiftsSimpleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShiftsMapper {

    ShiftsAllDto shiftsToShiftsAllDto(Shifts shifts);
    Shifts shiftsAllDtoToShifts(ShiftsAllDto shiftsAllDto);
    ShiftsSimpleDto shiftsToShiftsSimpleDto(Shifts shifts);
    Shifts shiftsSimpleDtoToShifts(ShiftsSimpleDto shiftsSimpleDto);
}
