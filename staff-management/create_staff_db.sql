CREATE DATABASE  IF NOT EXISTS staff_db;
USE staff_db;

--
-- Table structure for table `student`actor
--
SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS staff_shifts;
DROP TABLE IF EXISTS shifts;
DROP TABLE IF EXISTS staff;
DROP TABLE IF EXISTS position;
DROP TABLE IF EXISTS schedules;

SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE `position` (
  position_id INT NOT NULL AUTO_INCREMENT,
  position_title VARCHAR(45) DEFAULT NULL,
  position_description VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (position_id),
  UNIQUE KEY UC_Position (position_title)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE staff (
  staff_id INT NOT NULL AUTO_INCREMENT,
  staff_name VARCHAR(255) DEFAULT NULL,
  staff_dob DATE,
  staff_hire_date DATE,
  position_id INT,
  PRIMARY KEY (staff_id),
  FOREIGN KEY (position_id) REFERENCES `position`(position_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE shifts (
  shift_id INT NOT NULL AUTO_INCREMENT,
  shift_start_time TIME,
  shift_end_time TIME,
  shift_date DATE,
  PRIMARY KEY (shift_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- Associative table for the ManyToMany relationship between staff and shifts
CREATE TABLE staff_shifts (
  staff_id INT,
  shift_id INT,
  PRIMARY KEY (staff_id, shift_id),
  FOREIGN KEY (staff_id) REFERENCES staff(staff_id),
  FOREIGN KEY (shift_id) REFERENCES shifts(shift_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE schedules (
  schedule_id INT NOT NULL AUTO_INCREMENT,
  movie_id INT,
  schedule_start_time TIME,
  schedule_end_time TIME,
  schedule_screen_date DATE,
  schedule_available_seats INT,
  schedule_total_seats INT,
  PRIMARY KEY (schedule_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
