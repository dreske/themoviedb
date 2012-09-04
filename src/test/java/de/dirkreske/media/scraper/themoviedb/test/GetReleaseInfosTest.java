package de.dirkreske.media.scraper.themoviedb.test;

import de.dirkreske.media.scraper.themoviedb.TheMovieDB;
import de.dirkreske.media.scraper.themoviedb.exception.NotFoundException;
import de.dirkreske.media.scraper.themoviedb.model.ReleaseInfo;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Dirk Reske
 */
public class GetReleaseInfosTest {

	@Test
	public void getReleaseInfos() {
		TheMovieDB tmdb = new TheMovieDB();
		List<ReleaseInfo> result = tmdb.getReleaseInfos(11);
		assertNotNull("Result may not be null", result);
		assertTrue("There should be at least one release info", result.size() > 0);
	}

	@Test(expected = NotFoundException.class)
	public void testUnkownId() {
		TheMovieDB tmdb = new TheMovieDB();
		tmdb.getReleaseInfos(0);
	}
}
