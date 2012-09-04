package de.dirkreske.media.scraper.themoviedb.test;

import de.dirkreske.media.scraper.themoviedb.TheMovieDB;
import de.dirkreske.media.scraper.themoviedb.model.MovieExtended;
import de.dirkreske.media.scraper.themoviedb.model.PaginatedResult;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Dirk Reske
 */
public class GetGenreMoviesTest {

	@Test
	public void getGenreMovies() {
		TheMovieDB theMovieDB = new TheMovieDB();
		PaginatedResult<MovieExtended> genreMovies = theMovieDB.getGenreMovies(28);
		assertNotNull("genreMovies may not be null", genreMovies);
		assertEquals("should be first page", 1, genreMovies.getPage());
	}

	@Test
	public void getGenreMoviesPageTwo() {
		TheMovieDB theMovieDB = new TheMovieDB();
		PaginatedResult<MovieExtended> genreMovies = theMovieDB.getGenreMovies(28, 2);
		assertNotNull("genreMovies may not be null", genreMovies);
		assertEquals("should be second page", 2, genreMovies.getPage());
	}
}
