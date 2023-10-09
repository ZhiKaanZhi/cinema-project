package com.staff.staffmanagement.mapstruct.mappers;

import com.staff.staffmanagement.entity.Position;
import com.staff.staffmanagement.entity.Shifts;
import com.staff.staffmanagement.entity.Staff;
import com.staff.staffmanagement.mapstruct.dtos.StaffAllDto;
import com.staff.staffmanagement.mapstruct.dtos.StaffSimpleDto;
import com.staff.staffmanagement.repository.PositionRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface StaffMapper {

    @Mapping(source = "staffPosition", target = "staffPositionTitle")
    @Mapping(source = "staffShifts", target = "staffShiftsIDs", qualifiedByName = "shiftSetToIdList")
    StaffAllDto staffToStaffAllDto(Staff staff);

    @Mapping(source = "staffPosition", target = "staffPositionTitle")
    StaffSimpleDto staffToStaffSimpleDto(Staff staff);

    @Mapping(source = "staffPositionTitle", target = "staffPosition")
    Staff staffAllDtoToStaff(StaffAllDto staffAllDto);

    @Mapping(source = "staffPositionTitle", target = "staffPosition.positionTitle")
    Staff staffSimpleDtoToStaff(StaffSimpleDto staffSimpleDto);

    @Named("shiftSetToIdList")
    default List<Integer> shiftSetToIdList(Set<Shifts> shifts) {
        return shifts.stream().map(Shifts::getShiftID).collect(Collectors.toList());
    }

}
