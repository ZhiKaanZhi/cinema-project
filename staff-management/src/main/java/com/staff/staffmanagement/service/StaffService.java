package com.staff.staffmanagement.service;

import com.staff.staffmanagement.entity.Position;
import com.staff.staffmanagement.entity.Staff;
import com.staff.staffmanagement.mapstruct.dtos.StaffAllDto;
import com.staff.staffmanagement.mapstruct.mappers.StaffMapper;
import com.staff.staffmanagement.repository.PositionRepository;
import com.staff.staffmanagement.repository.StaffRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private StaffMapper staffMapper;

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public StaffAllDto getStaffById(int staffId) {
        Staff staff = staffRepository.findById(staffId)
                .orElse(null);
        return staffMapper.staffToStaffAllDto(staff);
    }

    @Transactional
    public StaffAllDto createStaff(StaffAllDto staffAllDto) {
        Position position = positionRepository.findByTitle(staffAllDto.getStaffPositionTitle());
        if(position == null) {
            return null;
        }

        Staff staff = staffMapper.staffAllDtoToStaff(staffAllDto);
        staff.setStaffPosition(position);

        staff = staffRepository.saveAndFlush(staff);
        return staffMapper.staffToStaffAllDto(staff);
    }

    @Transactional
    public void deleteStaff(Integer id) {
        staffRepository.deleteById(id);
    }
}
