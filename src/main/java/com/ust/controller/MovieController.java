package com.ust.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.enity.Movie;
import com.ust.service.MovieService;

@RestController
@RequestMapping("/api/v1")
public class MovieController {

	@Autowired
	private MovieService service;

	@PostMapping("/saveMovie")
	public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
		return new ResponseEntity<>(service.saveMovie(movie), HttpStatus.CREATED);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Movie>> getAllMovie() {
		return new ResponseEntity<>(service.getAllMovies(), HttpStatus.OK);
	}

	@GetMapping("/getMovieById/{id}")
	public ResponseEntity<Movie> getMovieById(@PathVariable("id") long id) {
		return new ResponseEntity<>(service.getMovieById(id), HttpStatus.FOUND);
	}

}
