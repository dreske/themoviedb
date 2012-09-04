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
public class GetTopRatedMoviesTest {

	@Test
	public void getTopRatedMovies() {
		TheMovieDB theMovieDB = new TheMovieDB();
		PaginatedResult<MovieExtended> result = theMovieDB.getTopRatedMovies(1, "de");
		assertNotNull(result);
		assertTrue("Pages must be more than one", result.getTotalPages() > 1);
		assertEquals("Must be first page", 1, result.getPage());
	}

	@Test
	public void getTopRatedMoviesPageTwo() {
		TheMovieDB theMovieDB = new TheMovieDB();
		PaginatedResult<MovieExtended> result = theMovieDB.getTopRatedMovies(2, "de");
		assertNotNull(result);
		assertTrue("Pages must be more than one", result.getTotalPages() > 1);
		assertEquals("Must be second page", 2, result.getPage());
	}

}
