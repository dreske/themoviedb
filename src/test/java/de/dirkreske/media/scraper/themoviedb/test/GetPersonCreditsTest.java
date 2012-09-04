package de.dirkreske.media.scraper.themoviedb.test;

import de.dirkreske.media.scraper.themoviedb.TheMovieDB;
import de.dirkreske.media.scraper.themoviedb.model.PersonCredits;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author Dirk Reske
 */
public class GetPersonCreditsTest {

	@Test
	public void getPersonCredits() {
		TheMovieDB theMovieDB = new TheMovieDB();
		PersonCredits credits = theMovieDB.getPersonCredits(287, null);
		assertNotNull("credits may not be null", credits);
	}
}
