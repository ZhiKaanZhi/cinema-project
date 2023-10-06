package com.staff.staffmanagement.controller;

import com.staff.staffmanagement.mapstruct.dtos.PositionAllDto;
import com.staff.staffmanagement.mapstruct.dtos.PositionSimpleDto;
import com.staff.staffmanagement.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping
    public ResponseEntity<List<PositionAllDto>> getAllPositionsDetailed() {
        List<PositionAllDto> positions = positionService.getAllPositionsDetailed();
        if (positions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(positions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PositionAllDto> getPositionById(@PathVariable int id) {
        PositionAllDto position = positionService.getPositionById(id);
        if (position == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(position, HttpStatus.OK);
    }

    @GetMapping("/simple")
    public ResponseEntity<List<PositionSimpleDto>> getAllPositionsSimple() {
        List<PositionSimpleDto> positions = positionService.getAllPositionsSimple();
        if (positions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(positions, HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<PositionSimpleDto> getPositionSimpleByTitle(@PathVariable String title) {
        PositionSimpleDto position = positionService.getPositionSimpleByTitle(title);
        if (position == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(position, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PositionAllDto> savePosition(@RequestBody PositionAllDto positionAllDto) {
        PositionAllDto savedPosition = positionService.savePosition(positionAllDto);
        return new ResponseEntity<>(savedPosition, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePosition(@PathVariable int id) {
        positionService.deletePosition(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
