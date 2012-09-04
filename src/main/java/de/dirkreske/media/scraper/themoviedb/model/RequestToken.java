package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * @author Dirk Reske
 */
public class RequestToken implements Serializable {

	private static final long serialVersionUID = -1779441540621472865L;

	@JsonProperty("expires_at")
	private String expiresAt;

	@JsonProperty("request_token")
	private String requestToken;

	@JsonProperty("success")
	private boolean success;

	/**
	 * Gets the expiration date of the request token
	 *
	 * @return the expiration date
	 */
	public String getExpiresAt() {
		return expiresAt;
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
	 * Indicates whether the request token was created successfully.
	 *
	 * @return true if the token was created successfully; false if not
	 */
	public boolean isSuccess() {
		return success;
	}
}
