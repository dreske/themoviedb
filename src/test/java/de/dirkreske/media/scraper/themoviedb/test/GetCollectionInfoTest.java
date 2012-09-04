package de.dirkreske.media.scraper.themoviedb.test;

import de.dirkreske.media.scraper.themoviedb.TheMovieDB;
import de.dirkreske.media.scraper.themoviedb.exception.NotFoundException;
import de.dirkreske.media.scraper.themoviedb.model.CollectionInfo;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;


/**
 * @author Dirk Reske
 */
public class GetCollectionInfoTest {

	@Test
	public void getCollectionInfo() {
		TheMovieDB tmdb = new TheMovieDB();
		CollectionInfo result = tmdb.getCollectionInfo(10);
		assertNotNull(result);
		assertThat(result.getName(), is("Star Wars Collection"));
	}

	@Test
	public void getCollectionInfoWithLanguage() {
		TheMovieDB tmdb = new TheMovieDB();
		CollectionInfo result = tmdb.getCollectionInfo(10, "de");
		assertNotNull(result);
		assertThat(result.getName(), is("Star Wars Collection"));
	}

	@Test(expected = NotFoundException.class)
	public void getCollectionInfoWithInvalidId() {
		TheMovieDB tmdb = new TheMovieDB();
		tmdb.getCollectionInfo(-1);
	}
}
