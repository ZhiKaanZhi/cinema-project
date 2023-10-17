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

    /*@Mapping(source = "scheduleID", target = "scheduleID")
    @Mapping(source = "scheduleStartTime", target = "scheduleStartTime")
    @Mapping(source = "scheduleEndTime", target = "scheduleEndTime")
    @Mapping(source = "scheduleScreenDate", target = "scheduleScreenDate")
    @Mapping(source = "scheduleAvailableSeats", target = "scheduleAvailableSeats")
    @Mapping(source = "scheduleTotalSeats", target = "scheduleTotalSeats")*/
    ScheduleAllDto schedulesToScheduleAllDto(Schedules schedules);

    /*@Mapping(source = "scheduleID", target = "scheduleID")
    @Mapping(source = "scheduleStartTime", target = "scheduleStartTime")
    @Mapping(source = "scheduleEndTime", target = "scheduleEndTime")
    @Mapping(source = "scheduleScreenDate", target = "scheduleScreenDate")
    @Mapping(source = "scheduleAvailableSeats", target = "scheduleAvailableSeats")
    @Mapping(source = "scheduleTotalSeats", target = "scheduleTotalSeats")*/
    Schedules scheduleAllDtoToSchedules(ScheduleAllDto scheduleAllDto);

    /*@Mapping(source = "scheduleID", target = "scheduleID")
    @Mapping(source = "scheduleStartTime", target = "scheduleStartTime")
    @Mapping(source = "scheduleEndTime", target = "scheduleEndTime")
    @Mapping(source = "scheduleScreenDate", target = "scheduleScreenDate")
    @Mapping(source = "scheduleAvailableSeats", target = "scheduleAvailableSeats")
    @Mapping(source = "scheduleTotalSeats", target = "scheduleTotalSeats")*/
    SchedulesSimpleDto schedulesToSchedulesSimpleDto(Schedules schedules);

    /*@Mapping(source = "scheduleID", target = "scheduleID")
    @Mapping(source = "scheduleStartTime", target = "scheduleStartTime")
    @Mapping(source = "scheduleEndTime", target = "scheduleEndTime")
    @Mapping(source = "scheduleScreenDate", target = "scheduleScreenDate")
    @Mapping(source = "scheduleAvailableSeats", target = "scheduleAvailableSeats")
    @Mapping(source = "scheduleTotalSeats", target = "scheduleTotalSeats")*/
    Schedules schedulesSimpleDtoToSchedules(SchedulesSimpleDto schedulesSimpleDto);

}

