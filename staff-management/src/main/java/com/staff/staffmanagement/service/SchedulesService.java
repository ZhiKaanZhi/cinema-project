package com.staff.staffmanagement.service;

import com.staff.staffmanagement.entity.Schedules;
import com.staff.staffmanagement.mapstruct.dtos.ScheduleAllDto;
import com.staff.staffmanagement.mapstruct.dtos.SchedulesSimpleDto;
import com.staff.staffmanagement.mapstruct.mappers.SchedulesMapper;
import com.staff.staffmanagement.repository.SchedulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchedulesService {

    @Autowired
    private SchedulesRepository schedulesRepository;

    @Autowired
    private SchedulesMapper schedulesMapper;

    //@Autowired
    //private MovieService movieService; // Assume this is another service that communicates with the microservice managing movies.

    /**
     * Retrieves details of all schedules.
     *
     * @return a list of ScheduleAllDto containing details of all schedules.
     */
    public List<ScheduleAllDto> getAllScheduleDetails() {
        return schedulesRepository.findAll().stream()
                .map(schedulesMapper::schedulesToScheduleAllDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves simplified details of all schedules.
     *
     * @return a list of SchedulesSimpleDto containing simplified details of all schedules.
     */
    public List<SchedulesSimpleDto> getAllSchedulesSimple() {
        return schedulesRepository.findAll().stream()
                .map(schedulesMapper::schedulesToSchedulesSimpleDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves the simplified details of a specific schedule.
     *
     * @param id the ID of the desired schedule.
     * @return SchedulesSimpleDto containing simplified details of the specified schedule.
     */
    public SchedulesSimpleDto getSimpleScheduleById(Integer id) {
        return schedulesRepository.findById(id)
                .map(schedulesMapper::schedulesToSchedulesSimpleDto)
                .orElse(null);
    }

    /**
     * Retrieves detailed information of a specific schedule.
     *
     * @param id the ID of the desired schedule.
     * @return ScheduleAllDto containing detailed information of the specified schedule.
     */
    public ScheduleAllDto getScheduleDetailById(Integer id) {
        return schedulesRepository.findById(id)
                .map(schedulesMapper::schedulesToScheduleAllDto)
                .orElse(null);
    }

    /**
     * Saves a new schedule or updates an existing one.
     *
     * @param scheduleAllDto data transfer object containing the schedule's details.
     * @return ScheduleAllDto containing the saved schedule's details.
     */
    public ScheduleAllDto saveSchedule(ScheduleAllDto scheduleAllDto) {
        Schedules schedules = schedulesMapper.scheduleAllDtoToSchedules(scheduleAllDto);
        schedules = schedulesRepository.saveAndFlush(schedules);
        return schedulesMapper.schedulesToScheduleAllDto(schedules);
    }

    /**
     * Deletes a specific schedule.
     *
     * @param id the ID of the schedule to be deleted.
     */
    public void deleteSchedule(Integer id) {
        schedulesRepository.deleteById(id);
    }

    /**
     * Retrieves schedules by screening date.
     *
     * @param date the date to filter schedules by.
     * @return a list of ScheduleAllDto for schedules on the specified date.
     */
    public List<ScheduleAllDto> findSchedulesByScreenDate(Date date) {
        return schedulesRepository.findByScheduleScreenDate(date).stream()
                .map(schedulesMapper::schedulesToScheduleAllDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves schedules by start time.
     *
     * @param time the start time to filter schedules by.
     * @return a list of ScheduleAllDto for schedules with the specified start time.
     */
    public List<ScheduleAllDto> findSchedulesByStartTime(Time time) {
        return schedulesRepository.findByScheduleStartTime(time).stream()
                .map(schedulesMapper::schedulesToScheduleAllDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves schedules by end time.
     *
     * @param time the end time to filter schedules by.
     * @return a list of ScheduleAllDto for schedules with the specified end time.
     */
    public List<ScheduleAllDto> findSchedulesByEndTime(Time time) {
        return schedulesRepository.findByScheduleEndTime(time).stream()
                .map(schedulesMapper::schedulesToScheduleAllDto)
                .collect(Collectors.toList());
    }
}
