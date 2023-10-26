package com.staff.staffmanagement.service;

import com.staff.staffmanagement.auth.AuthenticationRequest;
import com.staff.staffmanagement.auth.AuthenticationResponse;
import com.staff.staffmanagement.auth.RegisterRequest;
import com.staff.staffmanagement.configuration.JwtService;
import com.staff.staffmanagement.entity.Staff;
import com.staff.staffmanagement.mapstruct.mappers.StaffMapper;
import com.staff.staffmanagement.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final StaffService staffService;
    private final StaffMapper staffMapper;
    private final PositionRepository positionRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerStaff(RegisterRequest request) {

        var staff = Staff.builder()
                .staffName(request.getStaffName())
                .staffUsername(request.getStaffUsername())
                .staffEmail(request.getStaffEmail())
                .staffPassword(request.getStaffPassword())
                .staffDOB(request.getStaffDOB())
                .staffHireDate(request.getStaffHireDate())
                .staffPosition(positionRepository.findByPositionTitle(request.getStaffPosition()))
                .build();
        staffService.registerStaffSimpleDto(staffMapper.staffToStaffSimpleDto(staff));
        var jwtToken = jwtService.generateToken(staff);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticateStaff(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getStaffUsername(),
                        request.getStaffPassword()
                )
        );

        var staff = staffService.getStaffSimpleDtoByStaffUsername(request.getStaffUsername());
        var jwtToken = jwtService.generateToken(staffMapper.staffSimpleDtoToStaff(staff));
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
