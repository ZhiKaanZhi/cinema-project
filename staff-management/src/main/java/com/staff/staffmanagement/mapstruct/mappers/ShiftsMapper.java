package com.staff.staffmanagement.mapstruct.mappers;

import com.staff.staffmanagement.entity.Shifts;
import com.staff.staffmanagement.entity.Staff;
import com.staff.staffmanagement.mapstruct.dtos.ShiftsAllDto;
import com.staff.staffmanagement.mapstruct.dtos.ShiftsSimpleDto;
import com.staff.staffmanagement.repository.StaffRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(componentModel = "spring", uses = {ShiftsMapper.StaffIdMapper.class})
public interface ShiftsMapper {

    @Mapping(source = "shiftStaff", target = "shiftStaffIDs")
    ShiftsAllDto shiftsToShiftsAllDto(Shifts shifts);

    @Mapping(source = "shiftStaffIDs", target = "shiftStaff")
    Shifts shiftsAllDtoToShifts(ShiftsAllDto shiftsAllDto);

    ShiftsSimpleDto shiftsToShiftsSimpleDto(Shifts shifts);

    Shifts shiftsSimpleDtoToShifts(ShiftsSimpleDto shiftsSimpleDto);

    // Nested Mapper for handling the conversion from Staff Set to ID List
    @Mapper(componentModel = "spring")
    public abstract class StaffIdMapper {

        @Autowired
        protected StaffRepository staffRepository;

        // Specify how to convert Staff to Integer (presumably the ID).
        public Integer staffToId(Staff staff) {
            return staff.getStaffID();  // Adjust if getter is named differently.
        }

        // Mapstruct will use the method defined above to convert Set<Staff> to List<Integer>.
        public abstract List<Integer> staffSetToIdList(Set<Staff> staff);

        public Set<Staff> idListToStaffSet(List<Integer> staffIds) {
            return staffIds.stream()
                    .map(staffRepository::findById)
                    .flatMap(optionalStaff -> optionalStaff.map(Stream::of).orElseGet(Stream::empty))
                    .collect(Collectors.toSet());
        }
    }
}

