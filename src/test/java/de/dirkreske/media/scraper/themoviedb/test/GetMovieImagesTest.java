package de.dirkreske.media.scraper.themoviedb.test;

import de.dirkreske.media.scraper.themoviedb.TheMovieDB;
import de.dirkreske.media.scraper.themoviedb.exception.NotFoundException;
import de.dirkreske.media.scraper.themoviedb.model.MovieImages;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author Dirk Reske
 */
public class GetMovieImagesTest {

	@Test
	public void getMovieImages() {
		TheMovieDB tmdb= new TheMovieDB();
		MovieImages result = tmdb.getMovieImages(11);
		assertNotNull("Result may not be null", result);
		assertNotNull("Backdrops may not be null", result.getBackdrops());
		assertNotNull("Posters may not be null", result.getPosters());
	}

	@Test(expected = NotFoundException.class)
	public void testUnknownId() {
		TheMovieDB tmdb= new TheMovieDB();
		tmdb.getMovieImages(0);
	}
}
