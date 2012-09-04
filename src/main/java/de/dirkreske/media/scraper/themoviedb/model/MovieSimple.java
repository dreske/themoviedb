package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * @author Dirk Reske
 */
public class MovieSimple implements Serializable {

	private static final long serialVersionUID = 7783437299007095870L;

	@JsonProperty("id")
	private int id;

	@JsonProperty("title")
	private String title;

	@JsonProperty("backdrop_path")
	private String backdropPath;

	@JsonProperty("poster_path")
	private String posterPath;

	@JsonProperty("release_date")
	private String releaseDate;

	/**
	 * Creates a new {@code MovieSimple} instance.
	 */
	public MovieSimple() {
	}

	/**
	 * Gets the id of this movie.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the title of this movie.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets the backdrop path of this movie.
	 *
	 * @return the backdrop path
	 */
	public String getBackdropPath() {
		return backdropPath;
	}

	/**
	 * Gets the poster path of this movie.
	 *
	 * @return the poster path
	 */
	public String getPosterPath() {
		return posterPath;
	}

	/**
	 * Gets the release date of this movie.
	 *
	 * @return the release date
	 */
	public String getReleaseDate() {
		return releaseDate;
	}
}
