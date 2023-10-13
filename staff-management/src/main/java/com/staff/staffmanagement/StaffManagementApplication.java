package com.staff.staffmanagement;

import com.staff.staffmanagement.entity.Position;
import com.staff.staffmanagement.entity.Shifts;
import com.staff.staffmanagement.entity.Staff;
import com.staff.staffmanagement.entity.enums.StaffTitle;
import com.staff.staffmanagement.mapstruct.dtos.PositionAllDto;
import com.staff.staffmanagement.mapstruct.dtos.ShiftsAllDto;
import com.staff.staffmanagement.mapstruct.dtos.StaffAllDto;
import com.staff.staffmanagement.mapstruct.mappers.PositionMapper;
import com.staff.staffmanagement.mapstruct.mappers.SchedulesMapper;
import com.staff.staffmanagement.mapstruct.mappers.ShiftsMapper;
import com.staff.staffmanagement.mapstruct.mappers.StaffMapper;
import com.staff.staffmanagement.service.PositionService;
import com.staff.staffmanagement.service.SchedulesService;
import com.staff.staffmanagement.service.ShiftsService;
import com.staff.staffmanagement.service.StaffService;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Time;

@SpringBootApplication
public class StaffManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StaffManagementApplication.class, args);
	}

	@Bean
	@Transactional
	public CommandLineRunner commandLineRunner(PositionService positionService, StaffService staffService, ShiftsService shiftsService, SchedulesService schedulesService, PositionMapper positionMapper, StaffMapper staffMapper, ShiftsMapper shiftsMapper, SchedulesMapper schedulesMapper) {

		return runner -> {

			addStaffExample(positionService, staffService, shiftsService, positionMapper, staffMapper, shiftsMapper);
			addSchedulesExample(schedulesService, schedulesMapper);

		};
	}

	private void addSchedulesExample(SchedulesService schedulesService, SchedulesMapper schedulesMapper) {
	}

	private void addStaffExample(PositionService positionService, StaffService staffService, ShiftsService shiftsService, PositionMapper positionMapper, StaffMapper staffMapper, ShiftsMapper shiftsMapper) {

		// Create and Save Positions
		Position positionManager = saveAndPrintPosition(positionService, positionMapper, new Position(StaffTitle.MANAGER, "Manager of the Cinema"), "Position 1 Saved");
		Position positionCashier1 = saveAndPrintPosition(positionService, positionMapper, new Position(StaffTitle.CASHIER, "Cashier of the Cinema"), "Position 2 Saved");
		Position positionCashier2 = saveAndPrintPosition(positionService, positionMapper, new Position(StaffTitle.CASHIER, "Cashier of the Cinema"), "Position 3 Saved");
		Position positionJanitor1 = saveAndPrintPosition(positionService, positionMapper, new Position(StaffTitle.JANITOR, "Shift janitor"), "Position 4 Saved");
		Position positionJanitor2 = saveAndPrintPosition(positionService, positionMapper, new Position(StaffTitle.JANITOR, "shift janitor"), "Position 5 Saved");
		Position positionJanitor3 = saveAndPrintPosition(positionService, positionMapper, new Position(StaffTitle.JANITOR, "shift janitor"), "Position 6 Saved");


		// Create, Save, and Assign Shifts
		Shifts shift1 = saveAndPrintShift(shiftsService, shiftsMapper, new Shifts(Time.valueOf("08:00:00"), Time.valueOf("16:00:00"), java.sql.Date.valueOf("2023-11-28")), "Shift 1 Saved");
		Shifts shift2 = saveAndPrintShift(shiftsService, shiftsMapper, new Shifts(Time.valueOf("16:00:00"), Time.valueOf("00:00:00"), java.sql.Date.valueOf("2023-11-28")), "Shift 2 Saved");
		Shifts shift3 = saveAndPrintShift(shiftsService, shiftsMapper, new Shifts(Time.valueOf("00:00:00"), Time.valueOf("08:00:00"), java.sql.Date.valueOf("2023-11-29")), "Shift 3 Saved");
		Shifts shift4 = saveAndPrintShift(shiftsService, shiftsMapper, new Shifts(Time.valueOf("08:00:00"), Time.valueOf("16:00:00"), java.sql.Date.valueOf("2023-11-29")), "Shift 4 Saved");
		Shifts shift5 = saveAndPrintShift(shiftsService, shiftsMapper, new Shifts(Time.valueOf("16:00:00"), Time.valueOf("00:00:00"), java.sql.Date.valueOf("2023-11-29")), "Shift 5 Saved");
		Shifts shift6 = saveAndPrintShift(shiftsService, shiftsMapper, new Shifts(Time.valueOf("00:00:00"), Time.valueOf("08:00:00"), java.sql.Date.valueOf("2023-11-30")), "Shift 6 Saved");

		// Create, Save Staff and Assign Shifts
		saveStaffAssignShiftAssignPositionAndPrint(staffService, staffMapper, new Staff("Konstantinos Malavazos", java.sql.Date.valueOf("1992-6-2"), java.sql.Date.valueOf("2023-11-27")), positionManager, shift1, "Staff 1 Saved");
		saveStaffAssignShiftAssignPositionAndPrint(staffService, staffMapper, new Staff("Person_Cashier1", java.sql.Date.valueOf("1982-5-22"), java.sql.Date.valueOf("2022-5-12")), positionCashier1, shift1, "Staff 2 Saved");
		saveStaffAssignShiftAssignPositionAndPrint(staffService, staffMapper, new Staff("Person_Cashier2", java.sql.Date.valueOf("1997-8-15"), java.sql.Date.valueOf("2021-2-2")), positionCashier2, shift2, "Staff 3 Saved");
		saveStaffAssignShiftAssignPositionAndPrint(staffService, staffMapper, new Staff("Person_Janitor_1", java.sql.Date.valueOf("1982-5-22"), java.sql.Date.valueOf("2022-5-12")), positionJanitor1, shift1, "Staff 4 Saved");
		saveStaffAssignShiftAssignPositionAndPrint(staffService, staffMapper, new Staff("Person_Janitor_2", java.sql.Date.valueOf("1997-8-15"), java.sql.Date.valueOf("2021-2-2")), positionJanitor2, shift2, "Staff 5 Saved");
		saveStaffAssignShiftAssignPositionAndPrint(staffService, staffMapper, new Staff("Person_Janitor_3", java.sql.Date.valueOf("1997-8-15"), java.sql.Date.valueOf("2021-2-2")), positionJanitor3, shift3, "Staff 6 Saved");

		System.out.println("Done!");

	}


	private Position saveAndPrintPosition(PositionService positionService, PositionMapper positionMapper, Position position, String message) {
		PositionAllDto responsePosition = positionService.savePosition(positionMapper.positionToPositionAllDto(position));
		position.setPositionID(responsePosition.getPositionID());
		System.out.println(message + ": " + responsePosition);
		return position;
	}

	private Shifts saveAndPrintShift(ShiftsService shiftsService, ShiftsMapper shiftsMapper, Shifts shift, String message) {
		ShiftsAllDto responseShift = shiftsService.saveShift(shiftsMapper.shiftsToShiftsAllDto(shift));
		shift.setShiftID(responseShift.getShiftID());
		System.out.println(message + ": " + responseShift);
		return shift;
	}


	private Staff saveStaffAssignShiftAssignPositionAndPrint(StaffService staffService, StaffMapper staffMapper, Staff staff, Position position, Shifts shift, String message) {
		staff.setStaffPosition(position);  // Assuming you have a setter for Position in Staff class
		staff.addShift(shift);  // Assigning a shift to the staff
		System.out.println("Before saving a Staff");
		StaffAllDto responseStaff = staffService.createStaffAllDto(staffMapper.staffToStaffAllDto(staff));
		System.out.println("After saving a Staff");
		staff.setStaffID(responseStaff.getStaffID());
		System.out.println(message + ": " + responseStaff);
		return staff;
	}
}
