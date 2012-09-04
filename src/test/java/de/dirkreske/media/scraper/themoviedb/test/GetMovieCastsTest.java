package de.dirkreske.media.scraper.themoviedb.test;

import de.dirkreske.media.scraper.themoviedb.TheMovieDB;
import de.dirkreske.media.scraper.themoviedb.exception.NotFoundException;
import de.dirkreske.media.scraper.themoviedb.model.MovieCasts;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Dirk Reske
 */
public class GetMovieCastsTest {

	@Test
	public void getMovieCasts() {
		TheMovieDB tmdb= new TheMovieDB();
		MovieCasts result = tmdb.getMovieCasts(11);
		assertNotNull("Result may not be null", result);
		assertEquals("Response id should be the same as the request id", 11, result.getId());

		assertNotNull("MovieCast may not be null", result.getCast());
		assertTrue("There should be at least one cast", result.getCast().size() > 0);

		assertNotNull("MovieCrew may not be null", result.getCrew());
		assertTrue("There should be at least one crew member", result.getCrew().size() > 0);
	}

	@Test(expected = NotFoundException.class)
	public void testUnkownId() {
		TheMovieDB tmdb= new TheMovieDB();
		tmdb.getMovieCasts(0);
	}
}
