package com.staff.staffmanagement.mapstruct.mappers;

import com.staff.staffmanagement.entity.Shifts;
import com.staff.staffmanagement.mapstruct.dtos.ShiftsAllDto;
import com.staff.staffmanagement.mapstruct.dtos.ShiftsSimpleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShiftsMapper {

    @Mapping(source = "shiftStaff.staffID", target = "shiftStaffID")
    ShiftsAllDto shiftsToShiftsAllDto(Shifts shifts);

    @Mapping(source = "shiftStaffID", target = "shiftStaff.staffID")
    Shifts shiftsAllDtoToShifts(ShiftsAllDto shiftsAllDto);


    ShiftsSimpleDto shiftsToShiftsSimpleDto(Shifts shifts);


    Shifts shiftsSimpleDtoToShifts(ShiftsSimpleDto shiftsSimpleDto);
}
