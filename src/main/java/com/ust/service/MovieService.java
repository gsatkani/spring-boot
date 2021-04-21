package com.ust.service;

import java.util.List;

import com.ust.enity.Movie;

public interface MovieService {

	public Movie saveMovie(Movie movie);

	public List<Movie> getAllMovies();

	public Movie getMovieById(long id);

	public Movie deleteMovie(long id);

	public Movie updateMovie(Movie movie);

}
