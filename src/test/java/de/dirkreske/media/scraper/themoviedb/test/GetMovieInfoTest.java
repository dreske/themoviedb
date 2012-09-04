package de.dirkreske.media.scraper.themoviedb.test;

import de.dirkreske.media.scraper.themoviedb.TheMovieDB;
import de.dirkreske.media.scraper.themoviedb.exception.NotFoundException;
import de.dirkreske.media.scraper.themoviedb.model.MovieDetailed;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author Dirk Reske
 */
public class GetMovieInfoTest {

    @Test
    public void getMovieInfo() {
		TheMovieDB tmdb= new TheMovieDB();
        MovieDetailed result = tmdb.getMovieInfo(11);
        assertNotNull(result);
        assertThat(result.getTitle(), is("Star Wars: Episode IV - A New Hope"));
    }

    @Test
    public void getMovieInfoWithLanguage() {
		TheMovieDB tmdb= new TheMovieDB();
        MovieDetailed result = tmdb.getMovieInfo(62177, "de");
        assertNotNull(result);
        assertThat(result.getTitle(), is("Merida - Legende der Highlands"));
        assertThat(result.getOriginalTitle(), is("Brave"));
    }

    @Test(expected = NotFoundException.class)
    public void getMovieInfoWithInvalidId() throws NotFoundException {
		TheMovieDB tmdb= new TheMovieDB();
        tmdb.getMovieInfo(-1);
    }
}
