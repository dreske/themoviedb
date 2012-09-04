package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dirk Reske
 */
public class MovieImages implements Serializable {

	private static final long serialVersionUID = 766694905576594584L;

	@JsonProperty("id")
	private int id;

	@JsonProperty("backdrops")
	private List<Image> backdrops;

	@JsonProperty("posters")
	private List<Image> posters;

	/**
	 * Creates a new {@code MovieImages} instance.
	 */
	public MovieImages() {
	}

	/**
	 * Gets the id of the associated movie.
	 *
	 * @return the movie's id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the backdrop images.
	 *
	 * @return the images list
	 */
	public List<Image> getBackdrops() {
		return backdrops;
	}

	/**
	 * Gets the posters.
	 *
	 * @return the poster list
	 */
	public List<Image> getPosters() {
		return posters;
	}
}
