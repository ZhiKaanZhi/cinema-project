package com.staff.staffmanagement.mapstruct.mappers;

import com.staff.staffmanagement.entity.Schedules;
import com.staff.staffmanagement.mapstruct.dtos.MovieDto;
import com.staff.staffmanagement.mapstruct.dtos.ScheduleAllDto;
import com.staff.staffmanagement.mapstruct.dtos.SchedulesSimpleDto;
import com.staff.staffmanagement.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SchedulesMapperDecorator implements SchedulesMapper {

    @Autowired
    @Qualifier("schedulesMapperImpl_")
    private SchedulesMapper delegate;

    @Autowired
    private MovieService movieService;

    @Override
    public ScheduleAllDto schedulesToScheduleAllDto(Schedules schedules) {
        // First, use MapStruct to do the main property mapping
        ScheduleAllDto dto = delegate.schedulesToScheduleAllDto(schedules);

        // Now, enrich the DTO with movie details using MovieService
        dto.setScheduleMovie(movieService.getMovieById(schedules.getScheduleMovieId()).block());

        return dto;
    }

    @Override
    public Schedules scheduleAllDtoToSchedules(ScheduleAllDto scheduleAllDto) {
        Schedules schedule = delegate.scheduleAllDtoToSchedules(scheduleAllDto);
        schedule.setScheduleMovieId(scheduleAllDto.getScheduleMovie().getMovieID());
        return schedule;
    }

    @Override
    public SchedulesSimpleDto schedulesToSchedulesSimpleDto(Schedules schedules) {

        SchedulesSimpleDto dto = delegate.schedulesToSchedulesSimpleDto(schedules);
        dto.setScheduleMovieTitle(delegate.schedulesToScheduleAllDto(schedules).getScheduleMovie().getMovieTitle());
        return dto;
    }

    @Override
    public Schedules schedulesSimpleDtoToSchedules(SchedulesSimpleDto schedulesSimpleDto) {
        Schedules entity = delegate.schedulesSimpleDtoToSchedules(schedulesSimpleDto);
        MovieDto movie = movieService.getMovieByTitle(schedulesSimpleDto.getScheduleMovieTitle()).block();
        if (movie == null) {
            entity.setScheduleMovieId(0);
        }
        else{
            entity.setScheduleMovieId(movieService.getMovieByTitle(schedulesSimpleDto.getScheduleMovieTitle()).block().getMovieID());
        }

        return entity;
    }

}
