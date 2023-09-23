CREATE DATABASE  IF NOT EXISTS `movies_db`;
USE `movies_db`;

--
-- Table structure for table `student`
--
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `actor`;
DROP TABLE IF EXISTS `director`;
DROP TABLE IF EXISTS `movie`;
DROP TABLE IF EXISTS `movie_actor`;
SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE `director` (
  `directorID` int NOT NULL AUTO_INCREMENT,
  `directorName`varchar(45) DEFAULT NULL,
  `directorNationality` varchar(45) DEFAULT NULL,
  `directorGender`  varchar(45) DEFAULT NULL,
  `directorDateOfBirth` DATE,
  PRIMARY KEY (`directorID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `movie` (
  `movieID` int NOT NULL AUTO_INCREMENT,
  `movieTitle`varchar(45) DEFAULT NULL,
  `movieDescription` varchar(45) DEFAULT NULL,
  `movieDurationInMin` int DEFAULT NULL,
  `moviePrice` long DEFAULT NULL,
  `movieReleaseDate` date,
  `directorID` int,
  PRIMARY KEY (`movieID`),
  FOREIGN KEY (directorID) REFERENCES director(directorID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



CREATE TABLE `actor` (
  `actorID` int NOT NULL AUTO_INCREMENT,
  `actorName`varchar(45) DEFAULT NULL,
  `actorNationality` varchar(45) DEFAULT NULL,
  `actorGender`  varchar(45) DEFAULT NULL,
  `actorDateOfBirth` DATE,
  PRIMARY KEY (`actorID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE movie_actor (
    movieID INT,
    actorID INT,
    PRIMARY KEY (movieID, actorID),
    FOREIGN KEY (movieID) REFERENCES movie(movieID),
    FOREIGN KEY (actorID) REFERENCES actor(actorID)
);
