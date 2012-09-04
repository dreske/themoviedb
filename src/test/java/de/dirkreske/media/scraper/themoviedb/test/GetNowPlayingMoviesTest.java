package de.dirkreske.media.scraper.themoviedb.test;

import de.dirkreske.media.scraper.themoviedb.TheMovieDB;
import de.dirkreske.media.scraper.themoviedb.model.PaginatedResult;
import de.dirkreske.media.scraper.themoviedb.model.MovieExtended;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author Dirk Reske
 */
public class GetNowPlayingMoviesTest {

	@Test
	public void getNowPlayingMovies() {
		TheMovieDB tmdb = new TheMovieDB();
		PaginatedResult<MovieExtended> result = tmdb.getNowPlayingMovies();
		assertNotNull("Result may not be null", result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidPageNumber() {
		TheMovieDB tmdb = new TheMovieDB();
		tmdb.getNowPlayingMovies(-1);
	}

}
