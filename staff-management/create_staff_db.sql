CREATE DATABASE  IF NOT EXISTS staff_db;
USE staff_db;

--
-- Table structure for table `student`actor
--
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS position;
DROP TABLE IF EXISTS staff;
DROP TABLE IF EXISTS shift;
DROP TABLE IF EXISTS schedules;
SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE `position` (
  position_id int NOT NULL AUTO_INCREMENT,
  position_title varchar(45) DEFAULT NULL,
  position_description varchar(255) DEFAULT NULL,
  PRIMARY KEY (position_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE staff (
  staff_id int NOT NULL AUTO_INCREMENT,
  staff_name varchar(255) DEFAULT NULL,
  staff_dob date,
  staff_hire_date date,
  position_id int,
  PRIMARY KEY (staff_id),
  FOREIGN KEY (position_id) REFERENCES `position`(position_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



CREATE TABLE shifts (
  shift_id int NOT NULL AUTO_INCREMENT,
  staff_id int,
  shift_start_time TIME,
  shift_end_time TIME,
  shift_date DATE,
  PRIMARY KEY (shift_id),
  FOREIGN KEY (staff_id) REFERENCES staff(staff_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE schedules (
  schedule_id int NOT NULL AUTO_INCREMENT,
  movie_id int,
  schedule_start_time TIME,
  schedule_end_time TIME,
  schedule_screen_date DATE,
  schedule_available_seats int,
  schedule_total_seats int,
  PRIMARY KEY (schedule_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
