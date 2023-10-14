package com.staff.staffmanagement.mapstruct.mappers;

import com.staff.staffmanagement.entity.Position;
import com.staff.staffmanagement.entity.Shifts;
import com.staff.staffmanagement.entity.Staff;
import com.staff.staffmanagement.mapstruct.dtos.PositionAllDto;
import com.staff.staffmanagement.mapstruct.dtos.ShiftsAllDto;
import com.staff.staffmanagement.mapstruct.dtos.ShiftsSimpleDto;
import com.staff.staffmanagement.repository.StaffRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(componentModel = "spring")
public interface ShiftsMapper {


    @Mapping(target="shiftID", source="shiftID")
    @Mapping(target="shiftStartTime", source="shiftStartTime")
    @Mapping(target="shiftEndTime", source="shiftEndTime")
    @Mapping(target="shiftDate", source="shiftDate")
    ShiftsAllDto shiftsToShiftsAllDto(Shifts shifts);

    @Mapping(target="shiftID", source="shiftID")
    @Mapping(target="shiftStartTime", source="shiftStartTime")
    @Mapping(target="shiftEndTime", source="shiftEndTime")
    @Mapping(target="shiftDate", source="shiftDate")
    Shifts shiftsAllDtoToShifts(ShiftsAllDto shiftsAllDto);

    @Mapping(target="shiftID", source="shiftID")
    @Mapping(target="shiftStartTime", source="shiftStartTime")
    @Mapping(target="shiftEndTime", source="shiftEndTime")
    @Mapping(target="shiftDate", source="shiftDate")
    ShiftsSimpleDto shiftsToShiftsSimpleDto(Shifts shifts);

    @Mapping(target="shiftID", source="shiftID")
    @Mapping(target="shiftStartTime", source="shiftStartTime")
    @Mapping(target="shiftEndTime", source="shiftEndTime")
    @Mapping(target="shiftDate", source="shiftDate")
    Shifts shiftsSimpleDtoToShifts(ShiftsSimpleDto shiftsSimpleDto);

    @AfterMapping
    default void afterShiftsMapping (@MappingTarget ShiftsAllDto target, Shifts source) {
        if(source != null && source.getShiftStaff() != null) {
            target.setShiftStaffIDs(source.getShiftStaff().stream()
                    .map(Staff::getStaffID)
                    .collect(Collectors.toList()));
        }
    }

    @AfterMapping
    default void dtoToEntityAfterMapping(@MappingTarget Shifts target, ShiftsAllDto source, StaffRepository staffRepository) {
        Set<Staff> staffSet = source.getShiftStaffIDs().stream()
                .map(staffRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        target.setShiftStaff(staffSet);

        // Update the relationship on the staff side
        for (Staff staff : staffSet) {
            staff.addShift(target); // Use the synchronization helper method
        }
    }

}

