package de.dirkreske.media.scraper.themoviedb.test;

import de.dirkreske.media.scraper.themoviedb.TheMovieDB;
import de.dirkreske.media.scraper.themoviedb.exception.NotFoundException;
import de.dirkreske.media.scraper.themoviedb.model.PersonDetailed;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author Dirk Reske
 */
public class GetPersonInfoTest {

	@Test
	public void getPersonInfo() {
		TheMovieDB tmdb = new TheMovieDB();
		PersonDetailed person = tmdb.getPersonInfo(287);
		assertNotNull(person);
		assertEquals("Brad Pitt", person.getName());
	}

	@Test(expected = NotFoundException.class)
	public void getPersonInfoWithInvalidId() {
		TheMovieDB tmdb = new TheMovieDB();
		tmdb.getPersonInfo(-1);
	}
}
