package com.ust.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ust.enity.Movie;
import com.ust.repository.MovieRepository;

@ExtendWith(MockitoExtension.class)
// used to register extensions for theannotated test class 

class MovieServiceImplTest {

	@Mock
	private MovieRepository movieRepo; // creates mock object for the MovieRepository
	@InjectMocks
	private MovieServiceImpl service;
	private Movie movie1;
	private Movie movie2;
	
	public Optional<Movie> value;
	public List<Movie> listMovies;
	
	@BeforeEach
	public void setUp() {
		// MockitoAnnotations.initMocks(this);
		movie1 = new Movie(null, "ABC", "Comedy", 2021);
		movie2 = new Movie(null, "Salt and Pepper", "Drama", 2020);
		value=Optional.of(movie1);
		listMovies=new ArrayList<Movie>();
		listMovies.add(movie1);
		listMovies.add(movie2);
	}
	@AfterEach
	public void tearDown() {
		movieRepo.deleteAll();
		movie1 = null;
		movie2 = null;
	}
	@Test
	void testSaveMovie() {
		when(movieRepo.save(any())).thenReturn(movie1);
		Movie save = null;
		save = movieRepo.save(movie1);
		assertNotNull(save);
		assertEquals("ABC", save.getName());
		verify(movieRepo, times(1)).save(any());

	}

	@Test
	public void testGetAllMovies() {
        service.saveMovie(movie1);
        service.saveMovie(movie2);
		when(service.getAllMovies()).thenReturn(listMovies);
		List<Movie> allMovies = service.getAllMovies();
		assertEquals(listMovies, allMovies);
		
		assertEquals("Comedy",allMovies.get(0).getGenre());		
		
		verify(movieRepo,times(2)).save(any());
		verify(movieRepo,times(1)).findAll();
		
		
		
	}

	@Test
	@Disabled
	void testGetMovieById() {

	}

	@Test
	void testDeleteMovie() {
		//movieRepo.save(movie1); 
		when(movieRepo.findById(1l)).thenReturn(value);
		movieRepo.save(movie1);
		Movie deleteMovie = service.deleteMovie(1);
		assertEquals("Comedy",deleteMovie.getGenre());
        verify(movieRepo,times(2)).findById(1l); 
		verify(movieRepo, times(1)).deleteById(1l);

	}

	@Test
	@Disabled
	void testUpdateMovie() {

	}

}
