package de.dirkreske.media.scraper.themoviedb.test;

import de.dirkreske.media.scraper.themoviedb.TheMovieDB;
import de.dirkreske.media.scraper.themoviedb.exception.NotFoundException;
import de.dirkreske.media.scraper.themoviedb.model.Translation;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * @author Dirk Reske
 */
public class GetMovieTranslationsTest {

	@Test
	public void getMovieTranslations() {
		TheMovieDB tmdb = new TheMovieDB();
		List<Translation> result = tmdb.getMovieTranslations(11);
		assertNotNull("Result may not be null", result);
	}

	@Test(expected = NotFoundException.class)
	public void testUnknownId() {
		TheMovieDB tmdb = new TheMovieDB();
		tmdb.getMovieTranslations(0);
	}
}
