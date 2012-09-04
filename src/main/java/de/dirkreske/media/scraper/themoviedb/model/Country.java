package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * @author Dirk Reske
 */
public class Country implements Serializable {

	private static final long serialVersionUID = 5117775415666535423L;

	@JsonProperty("iso_3166_1")
	private String countryCode;

	@JsonProperty("name")
	private String name;

	/**
	 * Creates a new {@code Country} instance.
	 */
	public Country() {
	}

	/**
	 * Gets the iso 3166-1 code of the country.
	 *
	 * @return the country code
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * Gets the name of this country.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
