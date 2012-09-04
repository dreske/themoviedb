package de.dirkreske.media.scraper.themoviedb;

/**
 * @author Dirk Reske
 */
public class AuthenticationRequest {

	private String requestToken;
	private String authenticationUrl;

	/**
	 * Creates a new {@code AuthenticationRequest} instance.
	 *
	 * @param requestToken      the request token
	 * @param authenticationUrl the authentication url
	 */
	public AuthenticationRequest(String requestToken, String authenticationUrl) {
		this.requestToken = requestToken;
		this.authenticationUrl = authenticationUrl;
	}

	/**
	 * Gets the request token.
	 *
	 * @return the request token
	 */
	public String getRequestToken() {
		return requestToken;
	}

	/**
	 * Gets the authentication url.
	 *
	 * @return the authentication url
	 */
	public String getAuthenticationUrl() {
		return authenticationUrl;
	}
}
