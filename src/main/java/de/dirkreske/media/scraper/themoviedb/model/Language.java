package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * @author Dirk Reske
 */
public class Language implements Serializable {

	private static final long serialVersionUID = -8742125380494249293L;

	@JsonProperty("iso_639_1")
	private String iso6391;

	@JsonProperty("name")
	private String name;

	/**
	 * Creates a new {@code Language} instance.
	 */
	public Language() {
	}

	/**
	 * Gets the iso 639-1 code of this language.
	 *
	 * @return the language code
	 */
	public String getIso6391() {
		return iso6391;
	}

	/**
	 * Gets the name of this language.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
