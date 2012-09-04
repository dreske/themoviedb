package de.dirkreske.media.scraper.themoviedb.test;

import de.dirkreske.media.scraper.themoviedb.TheMovieDB;
import de.dirkreske.media.scraper.themoviedb.model.MovieDetailed;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author Dirk Reske
 */
public class GetLatestMovieTest {

	@Test
	public void getLatestMovie() {
		TheMovieDB theMovieDB= new TheMovieDB();
		MovieDetailed result = theMovieDB.getLatestMovie();
		assertNotNull("result may not be null", result);
	}
}
