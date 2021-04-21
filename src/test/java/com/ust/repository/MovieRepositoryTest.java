package com.ust.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.ModelAndView;

import com.ust.enity.Movie;
import com.ust.model.MovieDto;

//@SpringBootTest
@DataJpaTest // Annotation for a JPA test that focuses only on JPA components.
public class MovieRepositoryTest {

	@Autowired
	private MovieRepository movieRepository;

	private Movie movie;
	private Movie movie1;

	@BeforeEach
	public void setUp() {
		movie = new Movie(null, "ABC", "Comedy", 2021);
		movie1 = new Movie(null, "Salt and Pepper", "Drama", 2020);
	}

	@AfterEach
	public void tearDown() {
		movieRepository.deleteAll();
		movie = null;
	}

	@Test

	public void saveMovieReturnSavedDetails() {
		Movie saveMovie = movieRepository.save(movie);
		assertNotNull(saveMovie);
		assertEquals(1, saveMovie.getId());
		assertEquals(saveMovie.getName(), movie.getName());

		Movie fetchMovie = movieRepository.findById(saveMovie.getId()).get();
		assertNotNull(fetchMovie);
		assertEquals(fetchMovie.getGenre(), movie.getGenre());

	}

	@Test
	public void deleteMovieByIdReturnMovie() {
		Movie fetchMovie = movieRepository.save(movie);
		movieRepository.deleteById(fetchMovie.getId());
		// check for delete
		Optional<Movie> findById = movieRepository.findById(fetchMovie.getId());
		// optional used for null check
		assertEquals(Optional.empty(), findById);

	}

	@Test
	public void getAllMovieDetaisReturnList() {
		movieRepository.save(movie);
		movieRepository.save(movie1);
		List<Movie> listMovies = movieRepository.findAll();
		assertEquals(2, listMovies.size());
		assertEquals("Salt and Pepper", listMovies.get(1).getName());
		assertEquals("Comedy", listMovies.get(0).getGenre());

	}

	@Test
	public void findByIdReturnMovie() {
		Movie movie1 = movieRepository.save(movie);
		Movie movie2 = movieRepository.save(movie1);

		Optional<Movie> findById = movieRepository.findById(movie1.getId());
		assertEquals(2021, findById.get().getYear());
		Optional<Movie> findById1 = movieRepository.findById(movie2.getId());
		assertEquals("Drama", findById1.get().getGenre());

	}

}
