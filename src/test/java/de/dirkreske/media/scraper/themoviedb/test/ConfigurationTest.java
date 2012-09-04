package de.dirkreske.media.scraper.themoviedb.test;

import de.dirkreske.media.scraper.themoviedb.TheMovieDB;
import de.dirkreske.media.scraper.themoviedb.exception.NotAuthorizedException;
import org.junit.Test;

/**
 * @author Dirk Reske
 */
public class ConfigurationTest {

	@Test(expected = NotAuthorizedException.class)
	public void testUnauthorized() {
		TheMovieDB tmdb = new TheMovieDB();
		tmdb.setApiKey(null);
		tmdb.getMovieInfo(11);
	}
}
