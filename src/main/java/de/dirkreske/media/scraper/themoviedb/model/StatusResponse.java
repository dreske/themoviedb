package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * @author Dirk Reske
 */
public class StatusResponse implements Serializable {

	private static final long serialVersionUID = 4897939452951003522L;

	@JsonProperty("status_code")
	private int statusCode;

	@JsonProperty("status_message")
	private String statusMessage;

	/**
	 * Creates a new {@code StatusResponse} instance.
	 */
	public StatusResponse() {
	}

	/**
	 * Gets the status code.
	 *
	 * @return the status code
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * Gets the status message.
	 *
	 * @return the status message
	 */
	public String getStatusMessage() {
		return statusMessage;
	}
}
