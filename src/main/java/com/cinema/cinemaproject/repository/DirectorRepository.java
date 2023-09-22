package com.cinema.cinemaproject.repository;

import com.cinema.cinemaproject.entity.Director;
import com.cinema.cinemaproject.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {
}
