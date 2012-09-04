package de.dirkreske.media.scraper.themoviedb.test;

import de.dirkreske.media.scraper.themoviedb.TheMovieDB;
import de.dirkreske.media.scraper.themoviedb.exception.NotFoundException;
import de.dirkreske.media.scraper.themoviedb.model.PaginatedResult;
import de.dirkreske.media.scraper.themoviedb.model.MovieExtended;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Dirk Reske
 */
public class GetCompanyMoviesTest {

	@Test
	public void getCompanyMovies() {
		TheMovieDB tmdb= new TheMovieDB();
		PaginatedResult<MovieExtended> result = tmdb.getCompanyMovies(3);
		assertNotNull("Result may not be null", result);
		assertTrue("There must be at least two pages", result.getTotalPages() >= 2);
	}

	@Test(expected = NotFoundException.class)
	public void getCompanyMoviesWithUnknownId() {
		TheMovieDB tmdb= new TheMovieDB();
		tmdb.getCompanyMovies(0);
	}
}
