package de.dirkreske.media.scraper.themoviedb.test;

import de.dirkreske.media.scraper.themoviedb.TheMovieDB;
import de.dirkreske.media.scraper.themoviedb.exception.NotAuthorizedException;
import de.dirkreske.media.scraper.themoviedb.model.AccountInfo;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Dirk Reske
 */
public class GetAccountInfoTest extends TmdbTestBase {

	@Test
	public void getAccountInfo() {
		TheMovieDB theMovieDB = new TheMovieDB();
		theMovieDB.setSessionId(getSessionId());

		AccountInfo accountInfo = theMovieDB.getAccountInfo();
		assertNotNull("accountInfo may not be null", accountInfo);
		assertEquals("dreske_test", accountInfo.getUsername());
		assertEquals("de", accountInfo.getLanguage());
		assertEquals("DE", accountInfo.getCountry());
		assertEquals(true, accountInfo.isIncludeAdult());
	}

	@Test(expected = NotAuthorizedException.class)
	public void getAccountInfoUnauthorized() {
		TheMovieDB theMovieDB = new TheMovieDB();
		theMovieDB.getAccountInfo();
	}
}
