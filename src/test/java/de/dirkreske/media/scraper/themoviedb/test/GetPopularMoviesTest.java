package de.dirkreske.media.scraper.themoviedb.test;

import de.dirkreske.media.scraper.themoviedb.TheMovieDB;
import de.dirkreske.media.scraper.themoviedb.model.MovieExtended;
import de.dirkreske.media.scraper.themoviedb.model.PaginatedResult;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Dirk Reske
 */
public class GetPopularMoviesTest {

	@Test
	public void getPopularMovies() {
		TheMovieDB theMovieDB = new TheMovieDB();
		PaginatedResult<MovieExtended> result = theMovieDB.getPopularMovies("de");
		assertNotNull(result);
		assertTrue("Pages must be more than one", result.getTotalPages() > 1);
		assertEquals("Must be the first page", 1, result.getPage());
	}

	@Test
	public void getPopularMoviesPageTwo() {
		TheMovieDB theMovieDB = new TheMovieDB();
		PaginatedResult<MovieExtended> result = theMovieDB.getPopularMovies(2);
		assertNotNull(result);
		assertTrue("Pages must be more than one", result.getTotalPages() > 1);
		assertEquals("Must be the second page", 2, result.getPage());
	}
}
