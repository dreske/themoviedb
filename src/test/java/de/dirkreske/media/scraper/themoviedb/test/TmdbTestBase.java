package de.dirkreske.media.scraper.themoviedb.test;

/**
 * @author Dirk Reske
 */
public abstract class TmdbTestBase {

	/**
	 * Returns a tmdb session id.
	 *
	 * @return a session id
	 */
	protected static String getSessionId() {
		return System.getProperty("themoviedb.session.id");
	}
}
