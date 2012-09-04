package de.dirkreske.media.scraper.themoviedb.test;

import de.dirkreske.media.scraper.themoviedb.TheMovieDB;
import de.dirkreske.media.scraper.themoviedb.exception.NotFoundException;
import de.dirkreske.media.scraper.themoviedb.model.MovieKeywords;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author Dirk Reske
 */
public class GetMovieKeywordsTest {

	@Test
	public void getMovieKeywords() {
		TheMovieDB tmdb= new TheMovieDB();
		MovieKeywords result = tmdb.getMovieKeywords(11);
		assertNotNull("Result may not be null", result);
		assertNotNull("Keywords may not be null", result.getKeywords());
	}

	@Test(expected = NotFoundException.class)
	public void testUnknownId() {
		TheMovieDB tmdb= new TheMovieDB();
		tmdb.getMovieKeywords(0);
	}
}
