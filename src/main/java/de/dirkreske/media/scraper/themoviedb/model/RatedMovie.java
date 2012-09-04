package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Dirk Reske
 */
public class RatedMovie extends MovieExtended {
	private static final long serialVersionUID = 5282249551940176118L;

	@JsonProperty("rating")
	private double rating;

	/**
	 * Created a new {@code RatedMovie} instance.
	 */
	public RatedMovie() {
	}

	/**
	 * Gets the current users rating on this movie.
	 *
	 * @return the rating
	 */
	public double getRating() {
		return rating;
	}
}
