package com.staff.staffmanagement.controller;

import com.staff.staffmanagement.mapstruct.dtos.ShiftsAllDto;
import com.staff.staffmanagement.mapstruct.dtos.ShiftsSimpleDto;
import com.staff.staffmanagement.service.ShiftsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/shifts")
public class ShiftsController {

    @Autowired
    private ShiftsService shiftsService;

    @GetMapping
    public ResponseEntity<List<ShiftsAllDto>> getAllShiftsDetails() {
        return new ResponseEntity<>(shiftsService.getAllShiftsDetails(), HttpStatus.OK);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<ShiftsAllDto> getAllShiftDetailById(@PathVariable Integer id) {
        ShiftsAllDto shiftsAllDto = shiftsService.getAllShiftDetailById(id);
        if (shiftsAllDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(shiftsAllDto, HttpStatus.OK);
    }

    @GetMapping("/{id}/simple")
    public ResponseEntity<ShiftsSimpleDto> getSimpleShiftById(@PathVariable Integer id) {
        ShiftsSimpleDto shiftsSimpleDto = shiftsService.getSimpleShiftById(id);
        if (shiftsSimpleDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(shiftsSimpleDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ShiftsAllDto> saveShift(@RequestBody ShiftsAllDto shiftsAllDto) {
        try {
            return new ResponseEntity<>(shiftsService.saveShift(shiftsAllDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShift(@PathVariable Integer id) {
        try {
            shiftsService.deleteShift(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<ShiftsAllDto>> findShiftsByDate(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        return new ResponseEntity<>(shiftsService.findShiftsByDate(date), HttpStatus.OK);
    }

    @GetMapping("/startTime/{time}")
    public ResponseEntity<List<ShiftsAllDto>> findShiftsByStartTime(@PathVariable @DateTimeFormat(pattern="HH:mm:ss") Time time) {
        List<ShiftsAllDto> shiftsAllDtoList = shiftsService.findShiftsByStartTime(time);
        if(shiftsAllDtoList == null || shiftsAllDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(shiftsAllDtoList, HttpStatus.OK);
    }

    @GetMapping("/endTime/{time}")
    public ResponseEntity<List<ShiftsAllDto>> findShiftsByEndTime(@PathVariable @DateTimeFormat(pattern="HH:mm:ss") Time time) {
        List<ShiftsAllDto> shiftsAllDtoList = shiftsService.findShiftsByEndTime(time);
        if(shiftsAllDtoList == null || shiftsAllDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(shiftsAllDtoList, HttpStatus.OK);
    }

}
