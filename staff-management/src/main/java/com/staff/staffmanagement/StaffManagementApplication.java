package com.staff.staffmanagement;

import com.staff.staffmanagement.entity.Position;
import com.staff.staffmanagement.entity.Schedules;
import com.staff.staffmanagement.entity.Shifts;
import com.staff.staffmanagement.entity.Staff;
import com.staff.staffmanagement.entity.enums.StaffTitle;
import com.staff.staffmanagement.mapstruct.dtos.*;
import com.staff.staffmanagement.mapstruct.mappers.PositionMapper;
import com.staff.staffmanagement.mapstruct.mappers.SchedulesMapper;
import com.staff.staffmanagement.mapstruct.mappers.ShiftsMapper;
import com.staff.staffmanagement.mapstruct.mappers.StaffMapper;
import com.staff.staffmanagement.service.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
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
	public CommandLineRunner commandLineRunner(PositionService positionService, StaffService staffService, ShiftsService shiftsService, SchedulesService schedulesService, PositionMapper positionMapper, StaffMapper staffMapper, ShiftsMapper shiftsMapper, SchedulesMapper schedulesMapper, MovieService movieService) {

		return runner -> {

			addStaffExample(positionService, staffService, shiftsService, positionMapper, staffMapper, shiftsMapper);
			addSchedulesExample(schedulesService, schedulesMapper, movieService);

			System.out.println("Server listening on port 8082: http://localhost:8082/");
		};
	}

	private void addSchedulesExample(SchedulesService schedulesService, SchedulesMapper schedulesMapper, MovieService movieService) {

		System.out.println("Before Fetching 1st Movie");
		MovieDto movie1 = movieService.getMovieById(1).block();
		System.out.println("After Fetching 1st Movie: " + movie1);
		Schedules schedule1 = new Schedules(movie1.getMovieID(),  Time.valueOf("17:00:00"), Time.valueOf("19:00:00"), java.sql.Date.valueOf("2023-11-28"), 100, 100 );
		ScheduleAllDto savedSchedule1 = schedulesService.saveSchedule(schedulesMapper.schedulesToScheduleAllDto(schedule1));
		System.out.println("Saved Schedule 1: "+ savedSchedule1);


		MovieDto movie2 = movieService.getMovieById(2).block();
		Schedules schedule2 = new Schedules(movie2.getMovieID(),  Time.valueOf("19:30:00"), Time.valueOf("21:00:00"), java.sql.Date.valueOf("2023-11-28"), 100, 100 );
		ScheduleAllDto savedSchedule2 = schedulesService.saveSchedule(schedulesMapper.schedulesToScheduleAllDto(schedule2));
		System.out.println("Saved Schedule 2: "+ savedSchedule2);

		System.out.println("Done!");

	}

	private void addStaffExample(PositionService positionService, StaffService staffService, ShiftsService shiftsService, PositionMapper positionMapper, StaffMapper staffMapper, ShiftsMapper shiftsMapper) {

		// Create and Save Positions
		Position positionManager = saveAndPrintPosition(positionService, positionMapper, new Position(StaffTitle.MANAGER, "Manager of the Cinema"), "Position 1 Saved");
		Position positionCashier1 = saveAndPrintPosition(positionService, positionMapper, new Position(StaffTitle.CASHIER, "Cashier of the Cinema"), "Position 2 Saved");
		Position positionCashier2 = saveAndPrintPosition(positionService, positionMapper, new Position(StaffTitle.CASHIER, "Cashier of the Cinema"), "Position 3 Saved");
		Position positionProjectionist = saveAndPrintPosition(positionService, positionMapper, new Position(StaffTitle.PROJECTIONIST, "Projectionist of the Cinema"), "Position 4 Saved");
		Position positionJanitor1 = saveAndPrintPosition(positionService, positionMapper, new Position(StaffTitle.JANITOR, "Shift janitor"), "Position 5 Saved");
		Position positionJanitor2 = saveAndPrintPosition(positionService, positionMapper, new Position(StaffTitle.JANITOR, "shift janitor"), "Position 6 Saved");
		Position positionJanitor3 = saveAndPrintPosition(positionService, positionMapper, new Position(StaffTitle.JANITOR, "shift janitor"), "Position 7 Saved");


		// Create, Save, and Assign Shifts
		Shifts shift1 = saveAndPrintShift(shiftsService, shiftsMapper, new Shifts(Time.valueOf("08:00:00"), Time.valueOf("16:00:00"), java.sql.Date.valueOf("2023-11-28")), "Shift 1 Saved");
		Shifts shift2 = saveAndPrintShift(shiftsService, shiftsMapper, new Shifts(Time.valueOf("16:00:00"), Time.valueOf("00:00:00"), java.sql.Date.valueOf("2023-11-28")), "Shift 2 Saved");
		Shifts shift3 = saveAndPrintShift(shiftsService, shiftsMapper, new Shifts(Time.valueOf("00:00:00"), Time.valueOf("08:00:00"), java.sql.Date.valueOf("2023-11-29")), "Shift 3 Saved");
		Shifts shift4 = saveAndPrintShift(shiftsService, shiftsMapper, new Shifts(Time.valueOf("08:00:00"), Time.valueOf("16:00:00"), java.sql.Date.valueOf("2023-11-29")), "Shift 4 Saved");
		Shifts shift5 = saveAndPrintShift(shiftsService, shiftsMapper, new Shifts(Time.valueOf("16:00:00"), Time.valueOf("00:00:00"), java.sql.Date.valueOf("2023-11-29")), "Shift 5 Saved");
		Shifts shift6 = saveAndPrintShift(shiftsService, shiftsMapper, new Shifts(Time.valueOf("00:00:00"), Time.valueOf("08:00:00"), java.sql.Date.valueOf("2023-11-30")), "Shift 6 Saved");
		Shifts shift7 = saveAndPrintShift(shiftsService, shiftsMapper, new Shifts(Time.valueOf("08:00:00"), Time.valueOf("16:00:00"), java.sql.Date.valueOf("2023-11-30")), "Shift 7 Saved");
		Shifts shift8 = saveAndPrintShift(shiftsService, shiftsMapper, new Shifts(Time.valueOf("16:00:00"), Time.valueOf("00:00:00"), java.sql.Date.valueOf("2023-11-30")), "Shift 8 Saved");
		Shifts shift9 = saveAndPrintShift(shiftsService, shiftsMapper, new Shifts(Time.valueOf("00:00:00"), Time.valueOf("08:00:00"), java.sql.Date.valueOf("2023-12-1")), "Shift 9 Saved");
		Shifts shift10 = saveAndPrintShift(shiftsService, shiftsMapper, new Shifts(Time.valueOf("08:00:00"), Time.valueOf("16:00:00"), java.sql.Date.valueOf("2023-12-1")), "Shift 10 Saved");
		Shifts shift11 = saveAndPrintShift(shiftsService, shiftsMapper, new Shifts(Time.valueOf("16:00:00"), Time.valueOf("00:00:00"), java.sql.Date.valueOf("2023-12-1")), "Shift 11 Saved");

		// Create, Save Staff and Assign Shifts
		Staff StaffManager = saveStaffAssignShiftAssignPositionAndPrint(staffService, staffMapper, new Staff("Konstantinos Malavazos", "kmalavazos", "kmalavazos@gmail.com", "012345678", java.sql.Date.valueOf("1992-6-2"), java.sql.Date.valueOf("2023-11-27")), positionManager, shift1, "Staff 1 Saved");
		Staff StaffCashier1 = saveStaffAssignShiftAssignPositionAndPrint(staffService, staffMapper, new Staff("Person_Cashier1", "cashier1", "Person_Cashier1@gmail.com", "123456789", java.sql.Date.valueOf("1982-5-22"), java.sql.Date.valueOf("2022-5-12")), positionCashier1, shift1, "Staff 2 Saved");
		Staff StaffCashier2 = saveStaffAssignShiftAssignPositionAndPrint(staffService, staffMapper, new Staff("Person_Cashier2", "cashier2", "Person_Cashier2@gmail.com", "123456789", java.sql.Date.valueOf("1997-8-15"), java.sql.Date.valueOf("2021-2-2")), positionCashier2, shift2, "Staff 3 Saved");
		Staff StaffProjectionist1 = saveStaffAssignShiftAssignPositionAndPrint(staffService, staffMapper, new Staff("Person_Projectionist1", "projectionist1", "Person_Projectionist1@gmail.com", "123456789", java.sql.Date.valueOf("1991-9-15"), java.sql.Date.valueOf("2022-2-21")), positionProjectionist, shift2, "Staff 4 Saved");
		Staff StaffProjectionist2 = saveStaffAssignShiftAssignPositionAndPrint(staffService, staffMapper, new Staff("Person_Projectionist2", "projectionist2", "Person_Projectionist2@gmail.com", "123456789", java.sql.Date.valueOf("1971-9-25"), java.sql.Date.valueOf("1990-2-21")), positionProjectionist, shift3, "Staff 5 Saved");
		Staff StaffJanitor1 = saveStaffAssignShiftAssignPositionAndPrint(staffService, staffMapper, new Staff("Person_Janitor_1", "janitor1", "Person_Janitor_1@gmail.com", "123456789", java.sql.Date.valueOf("1982-5-22"), java.sql.Date.valueOf("2022-5-12")), positionJanitor1, shift1, "Staff 6 Saved");
		Staff StaffJanitor2 = saveStaffAssignShiftAssignPositionAndPrint(staffService, staffMapper, new Staff("Person_Janitor_2", "janitor2", "Person_Janitor_2@gmail.com", "123456789", java.sql.Date.valueOf("1997-8-15"), java.sql.Date.valueOf("2021-2-2")), positionJanitor2, shift2, "Staff 7 Saved");
		Staff StaffJanitor3 = saveStaffAssignShiftAssignPositionAndPrint(staffService, staffMapper, new Staff("Person_Janitor_3", "janitor3", "Person_Janitor_3@gmail.com", "123456789", java.sql.Date.valueOf("1997-8-15"), java.sql.Date.valueOf("2021-2-2")), positionJanitor3, shift3, "Staff 8 Saved");

		UpdateStaffShifts(staffService, staffMapper, StaffManager, shift7);
		UpdateStaffShifts(staffService, staffMapper, StaffCashier1, shift7);
		UpdateStaffShifts(staffService, staffMapper, StaffCashier2, shift8);
		UpdateStaffShifts(staffService, staffMapper, StaffProjectionist1, shift8);
		UpdateStaffShifts(staffService, staffMapper, StaffProjectionist2, shift9);
		UpdateStaffShifts(staffService, staffMapper, StaffJanitor1, shift7);
		UpdateStaffShifts(staffService, staffMapper, StaffJanitor2, shift8);
		UpdateStaffShifts(staffService, staffMapper, StaffJanitor3, shift9);

		System.out.println("Done!");

	}

	private Staff UpdateStaffShifts(StaffService staffService, StaffMapper staffMapper, Staff staff, Shifts shift) {
		staff.addShift(shift);  // Assigning a shift to the staff
		StaffAllDto responseStaff = staffService.updateStaff(staffMapper.staffToStaffAllDto(staff));
		System.out.println("Staff Updated: " + responseStaff);
		return staff;

	}


	private Position saveAndPrintPosition(PositionService positionService, PositionMapper positionMapper, Position position, String message) {
		PositionAllDto responsePosition = positionService.savePosition(positionMapper.positionToPositionAllDto(position));
		position.setPositionID(responsePosition.getPositionID());
		System.out.println(message + ": " + responsePosition);
		return position;
	}

	private Shifts saveAndPrintShift(ShiftsService shiftsService, ShiftsMapper shiftsMapper, Shifts shift, String message) {
		ShiftsAllDto dto = shiftsMapper.shiftsToShiftsAllDto(shift);
		ShiftsAllDto responseShift = shiftsService.saveShift(dto);
		shift.setShiftID(responseShift.getShiftID());
		System.out.println(message + ": " + responseShift);
		return shift;
	}


	private Staff saveStaffAssignShiftAssignPositionAndPrint(StaffService staffService, StaffMapper staffMapper, Staff staff, Position position, Shifts shift, String message) {
		staff.setStaffPosition(position);  // Assuming you have a setter for Position in Staff class
		staff.addShift(shift);  // Assigning a shift to the staff
		System.out.println("Staff before saving: "+ staff);
		StaffAllDto responseStaff = staffService.registerStaffAllDto(staffMapper.staffToStaffAllDto(staff));
		System.out.println("Staff after saving: "+ staff);
		staff.setStaffID(responseStaff.getStaffID());
		System.out.println(message + ": " + responseStaff);
		return staff;
	}
}
