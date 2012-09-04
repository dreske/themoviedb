package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * @author Dirk Reske
 */
public class AccountInfo implements Serializable {

	private static final long serialVersionUID = 6540151610420938652L;

	@JsonProperty("id")
	private int id;

	@JsonProperty("include_adult")
	private boolean includeAdult;

	@JsonProperty("iso_3166_1")
	private String country;

	@JsonProperty("iso_639_1")
	private String language;

	@JsonProperty("name")
	private String name;

	@JsonProperty("username")
	private String username;

	/**
	 * Gets the account id.
	 *
	 * @return the account id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Indicates whether adult content should be included by default.
	 *
	 * @return true if adult content should be included; false if not
	 */

	public boolean isIncludeAdult() {
		return includeAdult;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Gets the language.
	 *
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
}
