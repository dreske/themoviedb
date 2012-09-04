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
public class GetSimilarMoviesTest {

	@Test
	public void getSimilarMovies() {
		TheMovieDB theMovieDB = new TheMovieDB();
		PaginatedResult<MovieExtended> result = theMovieDB.getSimilarMovies(11);
		assertNotNull("result may not be null", result);
		assertEquals("must be the first page", 1, result.getPage());
	}

	@Test
	public void getSimilarMoviesPageTwo() {
		TheMovieDB theMovieDB = new TheMovieDB();
		PaginatedResult<MovieExtended> result = theMovieDB.getSimilarMovies(11, 2);
		assertNotNull("result may not be null", result);
		assertEquals("must be the second page", 2, result.getPage());
	}

	@Test
	public void getSimilarMoviesWithLanguage() {
		TheMovieDB theMovieDB = new TheMovieDB();
		PaginatedResult<MovieExtended> result = theMovieDB.getSimilarMovies(11, "de");
		assertNotNull("result may not be null", result);
		assertEquals("must be the first page", 1, result.getPage());
	}

	@Test(expected = IllegalArgumentException.class)
	public void getSimilarMoviesInvalidPage() {
		TheMovieDB theMovieDB = new TheMovieDB();
		theMovieDB.getSimilarMovies(11, 0);
	}
}
