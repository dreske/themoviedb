package de.dirkreske.media.scraper.themoviedb.test;

import de.dirkreske.media.scraper.themoviedb.TheMovieDB;
import de.dirkreske.media.scraper.themoviedb.exception.NotFoundException;
import de.dirkreske.media.scraper.themoviedb.model.MovieTrailers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Dirk Reske
 */
public class GetMovieTrailersTest {

	@Test
	public void getMovieTrailers() {
		TheMovieDB tmdb = new TheMovieDB();
		MovieTrailers result = tmdb.getMovieTrailers(11);
		assertNotNull("Result may not be null", result);
		assertEquals("Response id should be the same as the request id", 11, result.getId());

		assertNotNull("MovieCrew may not be null", result.getYoutubeTrailers());
		assertTrue("There should be at least one youtube trailer", result.getYoutubeTrailers().size() > 0);
	}

	@Test(expected = NotFoundException.class)
	public void testUnkownId() {
		TheMovieDB tmdb = new TheMovieDB();
		tmdb.getMovieTrailers(0);
	}
}
