package com.cinema.cinemaproject.service;

import com.cinema.cinemaproject.mapstruct.dtos.MovieAllDto;
import com.cinema.cinemaproject.mapstruct.dtos.MovieDto;
import com.cinema.cinemaproject.entity.Movie;
import com.cinema.cinemaproject.mapstruct.mappers.MapStructMapper;
import com.cinema.cinemaproject.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService{
    @Autowired
    private final MovieRepository movieRepository;
    private final MapStructMapper mapstructMapper;

    public MovieService(MovieRepository movieRepository, MapStructMapper mapstructMapper) {
        this.movieRepository = movieRepository;
        this.mapstructMapper = mapstructMapper;
    }



    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public List<MovieDto> findAllMovieDtos() {
        List<Movie> result = movieRepository.findAll();
        List<MovieDto> movieDtoList = new ArrayList<MovieDto>();

        if(!result.isEmpty()) {
            for (Movie movie: result) {
                movieDtoList.add(mapstructMapper.movieToMovieDto(movie));
            }
        }
        else {
            return null;
        }

        return movieDtoList;

    }

    public MovieDto findById(int Id) {

        return mapstructMapper.movieToMovieDto(movieRepository.findById(Id).orElse(null));
    }

    @Transactional
    public void save(MovieAllDto movieAllDto) {
        movieRepository.save(mapstructMapper.movieAllDtoToMovie(movieAllDto));
    }

    @Transactional
    public void deleteById(int Id) {
        movieRepository.deleteById(Id);
    }


    public MovieDto findMovieByTitle(String title) {
        Optional<Movie> result = movieRepository.findByTitle(title);

        return mapstructMapper.movieToMovieDto(result.orElse(null));
    }

    public List<MovieDto> findMoviesByMinDuration(int minDuration) {
        Optional<List<Movie>> result = movieRepository.findMoviesByMinDuration(minDuration);

        List<MovieDto> movieDtoList = new ArrayList<MovieDto>();

        if(result.isPresent()) {
            for (Movie movie: result.get()) {
                movieDtoList.add(mapstructMapper.movieToMovieDto(movie));
            }
        }
        else {
            return null;
            //throw new NoSuchElementException("No movies found with minimum duration: "+ minDuration+" mins.");
        }

        return movieDtoList;
    }


    public List<MovieDto> findMoviesByMaxDuration(int maxDuration) {
        Optional<List<Movie>> result = movieRepository.findMoviesByMaxDuration(maxDuration);

        List<MovieDto> movieDtoList = new ArrayList<MovieDto>();

        if(result.isPresent()) {
            for (Movie movie: result.get()) {
                movieDtoList.add(mapstructMapper.movieToMovieDto(movie));
            }
        }
        else {
            return null;
            //throw new NoSuchElementException("No movies found with minimum duration: "+ maxDuration+" mins.");
        }

        return movieDtoList;
    }

    public List<MovieDto> findMoviesByDuration(int duration) {
        Optional<List<Movie>> result = movieRepository.findMoviesByDuration(duration);

        List<MovieDto> movieDtoList = new ArrayList<MovieDto>();

        if(result.isPresent()) {
            for (Movie movie: result.get()) {
                movieDtoList.add(mapstructMapper.movieToMovieDto(movie));
            }
        }
        else {
            return null;
            //throw new NoSuchElementException("No movies found with minimum duration: "+ duration+" mins.");
        }

        return movieDtoList;
    }

    public List<MovieDto> findMoviesByMinPrice(long minPrice) {
        Optional<List<Movie>> result = movieRepository.findMoviesByMinPrice(minPrice);

        List<MovieDto> movieDtoList = new ArrayList<MovieDto>();

        if(result.isPresent()) {
            for (Movie movie: result.get()) {
                movieDtoList.add(mapstructMapper.movieToMovieDto(movie));
            }
        }
        else {
            return null;
            //throw new NoSuchElementException("No movies found with minimum price: "+ minPrice+" euros.");
        }

        return movieDtoList;
    }

    public List<MovieDto> findMoviesByMaxPrice(long maxPrice) {
        Optional<List<Movie>> result = movieRepository.findMoviesByMaxPrice(maxPrice);

        List<MovieDto> movieDtoList = new ArrayList<MovieDto>();

        if(result.isPresent()) {
            for (Movie movie: result.get()) {
                movieDtoList.add(mapstructMapper.movieToMovieDto(movie));
            }
        }
        else {
            return null;
            //throw new NoSuchElementException("No movies found with maximum price: "+ maxPrice+" euros.");
        }

        return movieDtoList;
    }

    public List<MovieDto> findMoviesByPrice(long price) {
        Optional<List<Movie>> result = movieRepository.findMoviesByMaxPrice(price);

        List<MovieDto> movieDtoList = new ArrayList<MovieDto>();

        if(result.isPresent()) {
            for (Movie movie: result.get()) {
                movieDtoList.add(mapstructMapper.movieToMovieDto(movie));
            }
        }
        else {
            return null;
            //throw new NoSuchElementException("No movies found with price: "+ price+" euros.");
        }

        return movieDtoList;
    }

    public List<MovieDto> findMoviesByReleaseDate(Date movieReleaseDate) {
        Optional<List<Movie>> result = movieRepository.findMoviesByReleaseDate(movieReleaseDate);

        List<MovieDto> movieDtoList = new ArrayList<MovieDto>();

        if(result.isPresent()) {
            for (Movie movie: result.get()) {
                movieDtoList.add(mapstructMapper.movieToMovieDto(movie));
            }
        }
        else {
            return null;
            //throw new NoSuchElementException("No movies found with release date: "+ movieReleaseDate);
        }

        return movieDtoList;
    }


    public List<MovieDto> findMoviesByDirectorId(Integer director_ID){
        Optional<List<Movie>> result = movieRepository.findMoviesByDirectorId(director_ID);

        List<MovieDto> movieDtoList = new ArrayList<MovieDto>();

        if(result.isPresent()) {
            for (Movie movie: result.get()) {
                movieDtoList.add(mapstructMapper.movieToMovieDto(movie));
            }
        }
        else {
            return null;
            //throw new NoSuchElementException("No movies directed from director with ID: "+ director_ID);
        }

        return movieDtoList;
    }
}
