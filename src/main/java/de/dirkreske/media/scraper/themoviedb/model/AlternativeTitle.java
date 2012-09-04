package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * @author Dirk Reske
 */
public class AlternativeTitle implements Serializable {

	private static final long serialVersionUID = 4625619658419128809L;

	@JsonProperty("iso_3166_1")
	private String country;

	@JsonProperty("title")
	private String title;

	/**
	 * Creates a new {@code AlternativeTitle} instance.
	 */
	public AlternativeTitle() {
	}

	/**
	 * Gets the iso 3166-1 code of the country.
	 *
	 * @return the country code
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
}
