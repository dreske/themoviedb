package de.dirkreske.media.scraper.themoviedb.test;

import de.dirkreske.media.scraper.themoviedb.AuthenticationRequest;
import de.dirkreske.media.scraper.themoviedb.TheMovieDB;

/**
 * @author Dirk Reske
 */
public class AuthenticationTest {

	public void createRequestToken() {
		TheMovieDB theMovieDB = new TheMovieDB();
		AuthenticationRequest request = theMovieDB.createAuthenticationRequest();
	}

	public void finishAuthentication() {
		TheMovieDB theMovieDB = new TheMovieDB();
		theMovieDB.finishAuthentication(new AuthenticationRequest("xxxx",null));
	}
}
