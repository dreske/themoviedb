package de.dirkreske.media.scraper.themoviedb.test;

import de.dirkreske.media.scraper.themoviedb.model.PaginatedResult;
import de.dirkreske.media.scraper.themoviedb.TheMovieDB;
import de.dirkreske.media.scraper.themoviedb.model.MovieExtended;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Dirk Reske
 */
public class SearchMovieTest {

	@Test
	public void searchMovie() {
		TheMovieDB tmdb= new TheMovieDB();
		PaginatedResult<MovieExtended> result = tmdb.searchMovies("Star wars");
		assertNotNull(result);
		assertTrue(result.getTotalResults() > 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void searchMovieWithIllegalArgument() {
		TheMovieDB tmdb= new TheMovieDB();
		tmdb.searchMovies("", -1);
	}

	@Test(expected = RuntimeException.class)
	public void searchMovieWithEmptySearchString() {
		TheMovieDB tmdb= new TheMovieDB();
		tmdb.searchMovies("");
	}
}
