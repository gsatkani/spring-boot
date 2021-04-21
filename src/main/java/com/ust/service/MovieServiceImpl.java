package com.ust.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.enity.Movie;
import com.ust.repository.MovieRepository;

//Programmer 2 : Sandra 
@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Override
	public Movie saveMovie(Movie movie) {

		return movieRepository.save(movie);

	}

	@Override
	public List<Movie> getAllMovies() {

		return movieRepository.findAll();
	}

	@Override
	public Movie getMovieById(long id) {
		Optional<Movie> findById = movieRepository.findById(id);

		if (findById.isPresent()) {

			return findById.get();
		}
		return null;
	}

	@Override
	public Movie deleteMovie(long id) {

		Optional<Movie> findById = movieRepository.findById(id);
		movieRepository.findById(id);
		if (findById.isPresent()) {
			movieRepository.deleteById(id);
			return findById.get();
		}
		return null;

	}

	@Override
	public Movie updateMovie(Movie movie) {

		return null;
	}

}
