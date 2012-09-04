package de.dirkreske.media.scraper.themoviedb.test;

import de.dirkreske.media.scraper.themoviedb.TheMovieDB;
import de.dirkreske.media.scraper.themoviedb.model.GenreSimple;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * @author Dirk Reske
 */
public class GetGenresTest {

	@Test
	public void getGenres() {
		TheMovieDB theMovieDB = new TheMovieDB();
		List<GenreSimple> genres = theMovieDB.getGenres(null);
		assertNotNull("genres may not be null", genres);
	}

	@Test()
	public void getGenresWithLanguage() {
		TheMovieDB theMovieDB = new TheMovieDB();
		List<GenreSimple> genres = theMovieDB.getGenres("de");
		for (GenreSimple genreSimple : genres) {
			if ("Abenteuer".equals(genreSimple.getName())) {
				return;
			}
		}
		fail("'Abenteuer' not found on german genre list");
	}
}
