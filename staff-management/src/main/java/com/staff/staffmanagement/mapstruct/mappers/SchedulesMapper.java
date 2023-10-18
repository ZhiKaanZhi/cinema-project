package com.staff.staffmanagement.mapstruct.mappers;

import com.staff.staffmanagement.entity.Position;
import com.staff.staffmanagement.entity.Schedules;
import com.staff.staffmanagement.entity.Staff;
import com.staff.staffmanagement.mapstruct.dtos.PositionAllDto;
import com.staff.staffmanagement.mapstruct.dtos.ScheduleAllDto;
import com.staff.staffmanagement.mapstruct.dtos.SchedulesSimpleDto;
import com.staff.staffmanagement.repository.StaffRepository;
import com.staff.staffmanagement.service.MovieService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {MovieService.class})
@DecoratedWith(SchedulesMapperDecorator.class)
public interface SchedulesMapper {
    ScheduleAllDto schedulesToScheduleAllDto(Schedules schedules);
    Schedules scheduleAllDtoToSchedules(ScheduleAllDto scheduleAllDto);
    SchedulesSimpleDto schedulesToSchedulesSimpleDto(Schedules schedules);
    Schedules schedulesSimpleDtoToSchedules(SchedulesSimpleDto schedulesSimpleDto);

}

