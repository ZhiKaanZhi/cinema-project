package com.staff.staffmanagement.controller;

import com.staff.staffmanagement.mapstruct.dtos.ScheduleAllDto;
import com.staff.staffmanagement.mapstruct.dtos.SchedulesSimpleDto;
import com.staff.staffmanagement.service.SchedulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class SchedulesController {

    @Autowired
    private SchedulesService schedulesService;

    @GetMapping
    public ResponseEntity<List<ScheduleAllDto>> getAllSchedules() {
        List<ScheduleAllDto> schedules = schedulesService.getAllScheduleDetails();
        if (schedules.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("/simple")
    public ResponseEntity<List<SchedulesSimpleDto>> getAllSchedulesSimple() {
        List<SchedulesSimpleDto> schedules = schedulesService.getAllSchedulesSimple();
        if (schedules.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ScheduleAllDto>> getScheduleById(@PathVariable Integer id) {
        return schedulesService.getScheduleDetailById(id)
                .map(schedule -> new ResponseEntity<>(schedule, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ScheduleAllDto> saveSchedule(@RequestBody ScheduleAllDto scheduleAllDto) {
        ScheduleAllDto savedSchedule = schedulesService.saveSchedule(scheduleAllDto);
        return new ResponseEntity<>(savedSchedule, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Integer id) {
        schedulesService.deleteSchedule(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/screen-date") // API call: /api/schedules/screen-date?date=2023-01-01
    public ResponseEntity<List<ScheduleAllDto>> getSchedulesByScreenDate(@RequestParam Date date) {
        List<ScheduleAllDto> schedules = schedulesService.findSchedulesByScreenDate(date);
        if (schedules.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("/start-time")
    public ResponseEntity<List<ScheduleAllDto>> getSchedulesByStartTime(@RequestParam Time time) {
        List<ScheduleAllDto> schedules = schedulesService.findSchedulesByStartTime(time);
        if (schedules.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("/end-time")
    public ResponseEntity<List<ScheduleAllDto>> getSchedulesByEndTime(@RequestParam Time time) {
        List<ScheduleAllDto> schedules = schedulesService.findSchedulesByEndTime(time);
        if (schedules.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }
}