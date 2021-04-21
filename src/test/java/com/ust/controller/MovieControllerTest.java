package com.ust.controller;

import static org.hamcrest.CoreMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ust.enity.Movie;
import com.ust.service.MovieService;

@ExtendWith(MockitoExtension.class)
public class MovieControllerTest {
	
	@Autowired
	// Main entry point for server-side Spring MVC test support.
	private MockMvc mockMvc;

	@Mock
	private MovieService service;

	@InjectMocks
	private MovieController controller;

	
	private Movie movie1;
	private Movie movie2;
	private List<Movie> listMovie;

	@BeforeEach
	public void setUp() {
		// Build a MockMvc instance by registering one or more @Controller instances and
		// configuring Spring MVC
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		movie1 = new Movie(1l, "ABC", "Comedy", 2021);
		movie2 = new Movie(2l, "Salt and Pepper", "Drama", 2020);
		listMovie = new ArrayList<>();
		listMovie.add(movie1);
		listMovie.add(movie2);

	}

	@AfterEach
	public void tearDown() {
		movie1 = null;
		movie2 = null;
		listMovie = null;
	}

	@Test
	public void saveMovieAndReturnSavedDetails() throws Exception {
		// when(service.saveMovie(any())).then
		when(service.saveMovie(any())).thenReturn(movie1);
		mockMvc.perform(post("/api/v1/saveMovie")
				.contentType(MediaType.APPLICATION_JSON)
				.content(convertStringtoJsonSring(movie1)))
				.andExpect(status().isCreated())
				.andDo(MockMvcResultHandlers.print());

		verify(service, timeout(1)).saveMovie(any());

	}
	@Test
	public void getAllMoviesReturnList() throws Exception{
		when(service.getAllMovies()).thenReturn(listMovie);
		mockMvc.perform(get("/api/v1/getAll")
				.contentType(MediaType.APPLICATION_JSON)
				.content(convertStringtoJsonSring(movie1)))
		        .andExpect(status().isOk())
		        .andDo(MockMvcResultHandlers.print());
		
		verify(service,times(1)).getAllMovies();
	}
	
	@Test 
	public void getMovieByIdReturnMovie() throws Exception {
		when(service.getMovieById(movie1.getId())).thenReturn(movie1);
		mockMvc.perform(get("/api/v1/getMovieById/1")
				.contentType(MediaType.APPLICATION_JSON).content(convertStringtoJsonSring(movie1)))
			.andExpect(status().isFound())
			.andDo(MockMvcResultHandlers.print());
		verify(service,times(1)).getMovieById(1);
	}
	
	
	public static String convertStringtoJsonSring(Object obj) throws Exception {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		}
		catch (Exception e) {
			throw new Exception();
		}
	}

}


