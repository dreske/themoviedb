package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * @author Dirk Reske
 */
public class Translation implements Serializable {

	private static final long serialVersionUID = -5040141211098508156L;

	@JsonProperty("english_name")
	private String englishName;

	@JsonProperty("iso_639_1")
	private String languageCode;

	@JsonProperty("name")
	private String name;

	/**
	 * Creates a new {@code Translation} instance.
	 */
	public Translation() {
	}

	/**
	 * Gets the english name of the language.
	 *
	 * @return the languages english name
	 */
	public String getEnglishName() {
		return englishName;
	}

	/**
	 * Gets the iso 639-1 language code of this translation.
	 *
	 * @return the language
	 */
	public String getLanguageCode() {
		return languageCode;
	}

	/**
	 * Gets the name of the language (in the language)
	 *
	 * @return the languages name
	 */
	public String getName() {
		return name;
	}
}
