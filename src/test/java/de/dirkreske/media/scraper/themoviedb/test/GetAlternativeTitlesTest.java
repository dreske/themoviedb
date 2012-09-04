package de.dirkreske.media.scraper.themoviedb.test;

import de.dirkreske.media.scraper.themoviedb.TheMovieDB;
import de.dirkreske.media.scraper.themoviedb.exception.NotFoundException;
import de.dirkreske.media.scraper.themoviedb.model.AlternativeTitle;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Dirk Reske
 */
public class GetAlternativeTitlesTest {

	@Test
	public void getAlternativeTitles() {
		TheMovieDB tmdb = new TheMovieDB();
		List<AlternativeTitle> result = tmdb.getAlternativeTitles(11);
		assertNotNull("Result may not be null", result);
		assertTrue("There must be more than one result", result.size() > 1);
	}

	@Test
	public void getAlternativeTitlesWithCountry() {
		TheMovieDB tmdb = new TheMovieDB();
		List<AlternativeTitle> result = tmdb.getAlternativeTitles(11, "de");
		assertNotNull("Result may not be null", result);
		assertEquals("There must be exactly one result", 1, result.size());
	}

	@Test(expected = NotFoundException.class)
	public void testUnknownId() {
		TheMovieDB tmdb = new TheMovieDB();
		tmdb.getAlternativeTitles(0);
	}
}
