package de.dirkreske.media.scraper.themoviedb.test;

import de.dirkreske.media.scraper.themoviedb.TheMovieDB;
import de.dirkreske.media.scraper.themoviedb.model.PaginatedResult;
import de.dirkreske.media.scraper.themoviedb.model.RatedMovie;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Dirk Reske
 */
public class GetRatedMoviesTest extends TmdbTestBase {
	public static final int LOCKOUT_MOVIE_ID = 81796;

	@Test
	public void getAccountRatedMovies() {
		TheMovieDB theMovieDB = new TheMovieDB();
		theMovieDB.setSessionId(getSessionId());
		PaginatedResult<RatedMovie> result = theMovieDB.getRatedMovies();
		assertNotNull("result may not be empty", result);
		assertEquals("there must be one rated movie", 2, result.getTotalResults());
		assertEquals("rating value must be 7.0", 7.0, result.getResults().get(0).getRating(), 0);
		assertEquals("movie name must be 'Brave'", "Brave", result.getResults().get(1).getTitle());

	}

	@Test
	public void getAccountRatedMoviesWithLanguage() {
		TheMovieDB theMovieDB = new TheMovieDB();
		theMovieDB.setSessionId(getSessionId());
		PaginatedResult<RatedMovie> result = theMovieDB.getRatedMovies("de");
		assertNotNull("result may not be empty", result);
		assertEquals("there must be one rated movie", 2, result.getTotalResults());
		assertEquals("movie name must be 'Merida - Legende der Highlands'", "Merida - Legende der Highlands", result.getResults().get(1).getTitle());
	}
}
