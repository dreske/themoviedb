package de.dirkreske.media.scraper.themoviedb.test;

import de.dirkreske.media.scraper.themoviedb.TheMovieDB;
import de.dirkreske.media.scraper.themoviedb.model.Configuration;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author Dirk Reske
 */
public class GetConfigurationTest {

    @Test
    public void testGetConfiguration() {
		TheMovieDB tmdb= new TheMovieDB();
        Configuration configuration = tmdb.getConfiguration();
        assertNotNull("Configuration may not be null", configuration);
        assertNotNull("Image configuration may not be null",
                configuration.getImageConfiguration());
    }
}
