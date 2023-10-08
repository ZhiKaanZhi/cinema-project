package com.staff.staffmanagement.mapstruct.mappers;

import com.staff.staffmanagement.entity.Shifts;
import com.staff.staffmanagement.entity.Staff;
import com.staff.staffmanagement.mapstruct.dtos.ShiftsAllDto;
import com.staff.staffmanagement.mapstruct.dtos.ShiftsSimpleDto;
import com.staff.staffmanagement.repository.StaffRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.Optional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(componentModel = "spring")
public interface ShiftsMapper {

    @Mapping(source = "shiftStaff", target = "shiftStaffIDs", qualifiedByName = "staffSetToIdList")
    ShiftsAllDto shiftsToShiftsAllDto(Shifts shifts);

    @Mapping(source = "shiftStaffIDs", target = "shiftStaff", qualifiedByName = "idListToStaffSet")
    Shifts shiftsAllDtoToShifts(ShiftsAllDto shiftsAllDto);


    ShiftsSimpleDto shiftsToShiftsSimpleDto(Shifts shifts);


    Shifts shiftsSimpleDtoToShifts(ShiftsSimpleDto shiftsSimpleDto);


    @Named("staffSetToIdList")
    default List<Integer> staffSetToIdList(Set<Staff> staff) {
        return staff.stream().map(Staff::getStaffID).collect(Collectors.toList());
    }

    @Named("idListToStaffSet")
    default Set<Staff> idListToStaffSet(List<Integer> staffIds, StaffRepository staffRepository) {
        return staffIds.stream()
                .map(staffRepository::findById)
                .flatMap(optionalStaff -> optionalStaff.map(Stream::of).orElseGet(Stream::empty))
                .collect(Collectors.toSet());
    }
}

