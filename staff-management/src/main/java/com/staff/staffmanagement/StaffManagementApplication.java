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

		Position positionManager = new Position(StaffTitle.MANAGER, "Manager of the Cinema");
		Position positionCashier1 = new Position(StaffTitle.CASHIER, "Cashier of the Cinema");
		Position positionJanitor1 = new Position(StaffTitle.JANITOR, "shift janitor");
		Position positionJanitor2 = new Position(StaffTitle.JANITOR, "shift janitor");
		Position positionJanitor3 = new Position(StaffTitle.JANITOR, "shift janitor");

		Staff staffManager = new Staff("Konstantinos Malavazos", java.sql.Date.valueOf("1992-6-2"), java.sql.Date.valueOf("2023-11-27"));
		Staff staffCashier1 = new Staff("Person_Cashier1", java.sql.Date.valueOf("1982-5-22"), java.sql.Date.valueOf("2022-5-12"));
		Staff staffCashier2 = new Staff("Person_Cashier2", java.sql.Date.valueOf("1997-8-15"), java.sql.Date.valueOf("2021-2-2"));
		Staff staffJanitor1 = new Staff("Person_Janitor_1", java.sql.Date.valueOf("1982-5-22"), java.sql.Date.valueOf("2022-5-12"));
		Staff staffJanitor2 = new Staff("Person_Janitor_2", java.sql.Date.valueOf("1997-8-15"), java.sql.Date.valueOf("2021-2-2"));
		Staff staffJanitor3 = new Staff("Person_Janitor_3", java.sql.Date.valueOf("1997-8-15"), java.sql.Date.valueOf("2021-2-2"));

		Shifts shift1 = new Shifts(Time.valueOf("08:00:00"), Time.valueOf("16:00:00"), java.sql.Date.valueOf("2023-11-28"));
		Shifts shift2 = new Shifts(Time.valueOf("16:00:00"), Time.valueOf("00:00:00"), java.sql.Date.valueOf("2023-11-28"));
		Shifts shift3 = new Shifts(Time.valueOf("00:00:00"), Time.valueOf("08:00:00"), java.sql.Date.valueOf("2023-11-29"));
		Shifts shift4 = new Shifts(Time.valueOf("08:00:00"), Time.valueOf("16:00:00"), java.sql.Date.valueOf("2023-11-29"));
		Shifts shift5 = new Shifts(Time.valueOf("16:00:00"), Time.valueOf("00:00:00"), java.sql.Date.valueOf("2023-11-29"));
		Shifts shift6 = new Shifts(Time.valueOf("00:00:00"), Time.valueOf("08:00:00"), java.sql.Date.valueOf("2023-11-30"));




		PositionAllDto responsePositionManager = positionService.savePosition(positionMapper.positionToPositionAllDto(positionManager));
		positionManager.setPositionID(responsePositionManager.getPositionID());
		System.out.println("Position 1 Saved: "+ responsePositionManager);

		PositionAllDto responsePositionCashier1 = positionService.savePosition(positionMapper.positionToPositionAllDto(positionCashier1));
		positionManager.setPositionID(responsePositionCashier1.getPositionID());
		System.out.println("Position 2 Saved: "+ responsePositionCashier1);

		PositionAllDto responsePositionJanitor1 = positionService.savePosition(positionMapper.positionToPositionAllDto(positionJanitor1));
		positionManager.setPositionID(responsePositionJanitor1.getPositionID());
		System.out.println("Position 3 Saved: "+ responsePositionJanitor1);

		PositionAllDto responsePositionJanitor2 = positionService.savePosition(positionMapper.positionToPositionAllDto(positionJanitor2));
		positionManager.setPositionID(responsePositionJanitor2.getPositionID());
		System.out.println("Position 4 Saved: "+ responsePositionJanitor2);

		PositionAllDto responsePositionJanitor3 = positionService.savePosition(positionMapper.positionToPositionAllDto(positionJanitor3));
		positionManager.setPositionID(responsePositionJanitor3.getPositionID());
		System.out.println("Position 5 Saved: "+ responsePositionJanitor3);

		ShiftsAllDto responseShift1 = shiftsService.saveShift(shiftsMapper.shiftsToShiftsAllDto(shift1));
		shift1.setShiftID(responseShift1.getShiftID());
		System.out.println("Shift 1 Saved: "+ responseShift1);

		ShiftsAllDto responseShift2 = shiftsService.saveShift(shiftsMapper.shiftsToShiftsAllDto(shift2));
		shift2.setShiftID(responseShift2.getShiftID());
		System.out.println("Shift 2 Saved: "+ responseShift2);

		ShiftsAllDto responseShift3 = shiftsService.saveShift(shiftsMapper.shiftsToShiftsAllDto(shift3));
		shift3.setShiftID(responseShift3.getShiftID());
		System.out.println("Shift 3 Saved: "+ responseShift3);

		ShiftsAllDto responseShift4 = shiftsService.saveShift(shiftsMapper.shiftsToShiftsAllDto(shift4));
		shift4.setShiftID(responseShift4.getShiftID());
		System.out.println("Shift 4 Saved: "+ responseShift4);

		ShiftsAllDto responseShift5 = shiftsService.saveShift(shiftsMapper.shiftsToShiftsAllDto(shift5));
		shift5.setShiftID(responseShift5.getShiftID());
		System.out.println("Shift 5 Saved: "+ responseShift5);

		ShiftsAllDto responseShift6 = shiftsService.saveShift(shiftsMapper.shiftsToShiftsAllDto(shift6));
		shift6.setShiftID(responseShift6.getShiftID());
		System.out.println("Shift 6 Saved: "+ responseShift6);



		StaffAllDto responseStaffManager = staffService.createStaffAllDto(staffMapper.staffToStaffAllDto(staffManager));
		staffManager.setStaffID(responseStaffManager.getStaffID());
		staffManager.addShift(shift1);
		System.out.println("Staff 1 Saved: "+ responseStaffManager);

		StaffAllDto responseStaffCashier1 = staffService.createStaffAllDto(staffMapper.staffToStaffAllDto(staffCashier1));
		staffCashier1.setStaffID(responseStaffCashier1.getStaffID());
		staffCashier1.addShift(shift1);
		System.out.println("Staff 2 Saved: "+ responseStaffCashier1);

		StaffAllDto responseStaffCashier2 = staffService.createStaffAllDto(staffMapper.staffToStaffAllDto(staffCashier2));
		staffCashier2.setStaffID(responseStaffCashier2.getStaffID());
		staffCashier2.addShift(shift2);
		System.out.println("Staff 3 Saved: "+ responseStaffCashier2);

		StaffAllDto responseStaffJanitor1 = staffService.createStaffAllDto(staffMapper.staffToStaffAllDto(staffJanitor1));
		staffJanitor1.setStaffID(responseStaffJanitor1.getStaffID());
		staffJanitor1.addShift(shift1);
		System.out.println("Staff 4 Saved: "+ responseStaffJanitor1);

		StaffAllDto responseStaffJanitor2 = staffService.createStaffAllDto(staffMapper.staffToStaffAllDto(staffJanitor2));
		staffJanitor2.setStaffID(responseStaffJanitor2.getStaffID());
		staffJanitor2.addShift(shift2);
		System.out.println("Staff 5 Saved: "+ responseStaffJanitor2);

		StaffAllDto responseStaffJanitor3 = staffService.createStaffAllDto(staffMapper.staffToStaffAllDto(staffJanitor3));
		staffJanitor3.setStaffID(responseStaffJanitor3.getStaffID());
		staffJanitor3.addShift(shift3);
		System.out.println("Staff 6 Saved: "+ responseStaffJanitor3);

		System.out.println("Done!");

	}
}
