package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dirk Reske
 */
public class ImageConfiguration implements Serializable {

	private static final long serialVersionUID = -7927222307586024696L;

	@JsonProperty("backdrop_sizes")
	private List<String> backdropSizes;

	@JsonProperty("base_url")
	private String baseUrl;

	@JsonProperty("poster_sizes")
	private List<String> posterSizes;

	@JsonProperty("profile_sizes")
	private List<String> profileSizes;

	@JsonProperty("logo_sizes")
	private List<String> logoSizes;

	/**
	 * Creates a new {@code ImageConfiguration} instance.
	 */
	public ImageConfiguration() {
	}

	/**
	 * Gets the backdrop sizes.
	 *
	 * @return the backdrop sizes
	 */
	public List<String> getBackdropSizes() {
		return backdropSizes;
	}

	/**
	 * Gets the base url for the images.
	 *
	 * @return the base url
	 */
	public String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * Gets the poster sizes.
	 *
	 * @return the poster sizes
	 */
	public List<String> getPosterSizes() {
		return posterSizes;
	}

	/**
	 * Gets the profile sizes.
	 *
	 * @return the profile sizes
	 */
	public List<String> getProfileSizes() {
		return profileSizes;
	}

	/**
	 * Gets the logo sizes.
	 *
	 * @return the logo sizes
	 */
	public List<String> getLogoSizes() {
		return logoSizes;
	}
}
