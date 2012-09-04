package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Dirk Reske
 */
public class MovieExtended extends MovieSimple {

	private static final long serialVersionUID = 213379745048330182L;

	@JsonProperty("original_title")
	private String originalTitle;

	@JsonProperty("popularity")
	private String popularity;

	@JsonProperty("adult")
	private boolean adult;

	@JsonProperty("vote_average")
	private double voteAverage;

	@JsonProperty("vote_count")
	private int voteCount;

	/**
	 * Creates a new {@code MovieExtended} instance.
	 */
	public MovieExtended() {
	}


	/**
	 * Gets the original title of this movie.
	 *
	 * @return the original title
	 */
	public String getOriginalTitle() {
		return originalTitle;
	}

	/**
	 * Gets the popularity of this movie.
	 *
	 * @return the popularity
	 */
	public String getPopularity() {
		return popularity;
	}

	/**
	 * Indicates whether this movie is an adult movie.
	 *
	 * @return true if this isan adult movie; false if not
	 */
	public boolean isAdult() {
		return adult;
	}

	/**
	 * Gets the average voting result of this movie.
	 *
	 * @return the average voting result
	 */
	public double getVoteAverage() {
		return voteAverage;
	}

	/**
	 * Gets the voting count of this movie.
	 *
	 * @return the voting count
	 */
	public int getVoteCount() {
		return voteCount;
	}
}
