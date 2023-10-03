package com.staff.staffmanagement.mapstruct.mappers;

import com.staff.staffmanagement.entity.Schedules;
import com.staff.staffmanagement.mapstruct.dtos.ScheduleAllDto;
import com.staff.staffmanagement.mapstruct.dtos.SchedulesSimpleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchedulesMapper {

    ScheduleAllDto schedulesToScheduleAllDto(Schedules schedules);
    Schedules scheduleAllDtoToSchedules(ScheduleAllDto scheduleAllDto);
    SchedulesSimpleDto schedulesToSchedulesSimpleDto(Schedules schedules);
    Schedules schedulesSimpleDtoToSchedules(SchedulesSimpleDto schedulesSimpleDto);
}
