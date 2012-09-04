package de.dirkreske.media.scraper.themoviedb.test;

import de.dirkreske.media.scraper.themoviedb.TheMovieDB;
import de.dirkreske.media.scraper.themoviedb.exception.NotFoundException;
import de.dirkreske.media.scraper.themoviedb.model.CompanyDetailed;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author Dirk Reske
 */
public class GetCompanyTest {

	@Test
	public void getCompany() {
		TheMovieDB tmdb= new TheMovieDB();
		CompanyDetailed result = tmdb.getCompany(3);
		assertNotNull("Result may not be null", result);
		assertNotNull("Parent company may not be null", result.getParentCompany());
	}

	@Test(expected = NotFoundException.class)
	public void getCompanyWithUnknownId() {
		TheMovieDB tmdb= new TheMovieDB();
		tmdb.getCompany(0);
	}
}
