CREATE DATABASE  IF NOT EXISTS movies_db;
USE movies_db;

--
-- Table structure for table `student`actor
--
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS actor;
DROP TABLE IF EXISTS director;
DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS movie_actor;
SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE director (
  director_id int NOT NULL AUTO_INCREMENT,
  director_name varchar(45) DEFAULT NULL,
  director_nationality varchar(45) DEFAULT NULL,
  director_gender  varchar(45) DEFAULT NULL,
  director_date_of_birth DATE,
  PRIMARY KEY (director_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE movie (
  movie_id int NOT NULL AUTO_INCREMENT,
  movie_title varchar(45) DEFAULT NULL,
  movie_description varchar(45) DEFAULT NULL,
  movie_duration_in_min int DEFAULT NULL,
  movie_price long DEFAULT NULL,
  movie_release_date date,
  director_id int,
  PRIMARY KEY (movie_id),
  FOREIGN KEY (director_id) REFERENCES director(director_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;



CREATE TABLE actor (
  actor_id int NOT NULL AUTO_INCREMENT,
  actor_name varchar(45) DEFAULT NULL,
  actor_nationality varchar(45) DEFAULT NULL,
  actor_gender  varchar(45) DEFAULT NULL,
  actor_date_of_birth DATE,
  PRIMARY KEY (actor_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE movie_actor (
    movie_id INT,
    actor_id INT,
    PRIMARY KEY (movie_id, actor_id),
    FOREIGN KEY (movie_id) REFERENCES movie(movie_id),
    FOREIGN KEY (actor_id) REFERENCES actor(actor_id)
);
