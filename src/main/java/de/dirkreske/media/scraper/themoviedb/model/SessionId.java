package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * @author Dirk Reske
 */
public class SessionId implements Serializable {

	private static final long serialVersionUID = 870667853664854161L;

	@JsonProperty("session_id")
	private String sessionId;

	@JsonProperty("success")
	private boolean success;

	/**
	 * Gets the session id.
	 *
	 * @return the session id
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * Indicates whether the session is authenticated.
	 *
	 * @return true if the authentication has passed; false if not
	 */
	public boolean isSuccess() {
		return success;
	}
}
