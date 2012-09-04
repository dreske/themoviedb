package de.dirkreske.media.scraper.themoviedb.test;

import de.dirkreske.media.scraper.themoviedb.TheMovieDB;
import de.dirkreske.media.scraper.themoviedb.model.PaginatedResult;
import de.dirkreske.media.scraper.themoviedb.model.PersonSimple;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * @author Dirk Reske
 */
public class SearchPersonTest {

	@Test
	public void searchPerson() {
		TheMovieDB tmdb = new TheMovieDB();
		PaginatedResult<PersonSimple> persons = tmdb.searchPerson("Brad Pitt", 1);

		assertNotNull("Search result must not be <null>", persons);
		assertTrue("There must be at least one result", persons.getTotalResults() >= 1);
		assertTrue(persons.getResults().get(0).getName().equals("Brad Pitt"));
	}
}
