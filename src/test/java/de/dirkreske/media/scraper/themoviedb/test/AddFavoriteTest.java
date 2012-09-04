package de.dirkreske.media.scraper.themoviedb.test;

import de.dirkreske.media.scraper.themoviedb.TheMovieDB;
import de.dirkreske.media.scraper.themoviedb.model.MovieExtended;
import de.dirkreske.media.scraper.themoviedb.model.PaginatedResult;
import de.dirkreske.media.scraper.themoviedb.model.StatusResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Dirk Reske
 */
public class AddFavoriteTest extends TmdbTestBase {

	@BeforeClass
	public static void removeAllFavorites() {
		TheMovieDB theMovieDB = new TheMovieDB();
		theMovieDB.setSessionId(getSessionId());
		PaginatedResult<MovieExtended> favorites = theMovieDB.getFavoriteMovies();
		for (MovieExtended movieExtended : favorites.getResults()) {
			theMovieDB.removeFavorite(movieExtended.getId());
		}
	}


	@Test
	public void addFavorite() {
		TheMovieDB theMovieDB = new TheMovieDB();
		theMovieDB.setSessionId(getSessionId());

		StatusResponse response = theMovieDB.addFavorite(11);
		assertNotNull("response my not be null", response);
		assertEquals("status code should be 1", 1, response.getStatusCode());

		response = theMovieDB.removeFavorite(11);
		assertNotNull("response my not be null", response);
		assertEquals("status code should be 13", 13, response.getStatusCode());
	}
}
