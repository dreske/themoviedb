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
public class GetFavoriteTest extends TmdbTestBase {

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
	public void getFavoritesTest() {
		TheMovieDB theMovieDB = new TheMovieDB();
		theMovieDB.setSessionId(getSessionId());

		//add movie to favorites
		StatusResponse response = theMovieDB.addFavorite(73);
		assertNotNull("response my not be null", response);
		assertEquals("status code should be 1", 1, response.getStatusCode());

		//get favorites
		PaginatedResult<MovieExtended> favorites = theMovieDB.getFavoriteMovies(1);
		assertNotNull("favorites may not be null", favorites);
		assertEquals("there should be one favorite", 1, favorites.getTotalResults());
		assertEquals("id of favorite should be 73", 73, favorites.getResults().get(0).getId());

		//remove favorites
		response = theMovieDB.removeFavorite(73);
		assertNotNull("response my not be null", response);
		assertEquals("status code should be 13", 13, response.getStatusCode());

		//get favorites
		favorites = theMovieDB.getFavoriteMovies(null);
		assertNotNull("favorites may not be null", favorites);
		assertEquals("there should be no favorites", 0, favorites.getTotalResults());
	}

	@Test(expected = IllegalArgumentException.class)
	public void getFavoritesWithInvalidPage() {
		TheMovieDB theMovieDB = new TheMovieDB();
		theMovieDB.setSessionId(getSessionId());
		theMovieDB.getFavoriteMovies(0);
	}
}
