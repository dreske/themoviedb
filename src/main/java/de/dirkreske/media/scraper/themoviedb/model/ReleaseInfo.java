package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * @author Dirk Reske
 */
public class ReleaseInfo implements Serializable {

	private static final long serialVersionUID = 8924572117068451915L;

	@JsonProperty("certification")
	private String certification;

	@JsonProperty("iso_3166_1")
	private String country;

	@JsonProperty("release_date")
	private String releaseDate;

	/**
	 * Creates a new {@code ReleaseInfo} instance.
	 */
	public ReleaseInfo() {
	}

	/**
	 * Gets the certification of this release.
	 *
	 * @return the certification
	 */
	public String getCertification() {
		return certification;
	}

	/**
	 * Gets the country of this release.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Gets the release date.
	 *
	 * @return the release date
	 */
	public String getReleaseDate() {
		return releaseDate;
	}
}
