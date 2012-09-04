package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Dirk Reske
 */
public class MovieCast extends CastBase {

	private static final long serialVersionUID = 5136138878647028743L;

	@JsonProperty("name")
	private String name;

	@JsonProperty("order")
	private int order;

	@JsonProperty("profile_path")
	private String profilePath;

	/**
	 * Creates a new {@code MovieCast} instance.
	 */
	public MovieCast() {
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
	 * Gets the order.
	 *
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * Gets the profile path.
	 *
	 * @return the profile path
	 */
	public String getProfilePath() {
		return profilePath;
	}
}
