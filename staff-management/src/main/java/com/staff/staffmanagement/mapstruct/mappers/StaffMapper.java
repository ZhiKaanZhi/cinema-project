package com.staff.staffmanagement.mapstruct.mappers;

import com.staff.staffmanagement.entity.Position;
import com.staff.staffmanagement.entity.Staff;
import com.staff.staffmanagement.mapstruct.dtos.StaffAllDto;
import com.staff.staffmanagement.mapstruct.dtos.StaffSimpleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface StaffMapper {

    @Mapping(source = "staffPositionTitle", target = "position", qualifiedByName = "titleToPosition")
    StaffAllDto staffToStaffAllDto(Staff staff);

    @Mapping(source = "position", target = "staffPositionTitle", qualifiedByName = "positionToTitle")
    Staff staffAllDtoToStaff(StaffAllDto staffAllDto);

    StaffSimpleDto staffToStaffSimpleDto(Staff staff);
    Staff staffSimpleDtoToStaff(StaffSimpleDto staffSimpleDto);

}
