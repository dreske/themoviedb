package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dirk Reske
 */
public class MovieCasts implements Serializable {

	private static final long serialVersionUID = -935699522238163803L;

	@JsonProperty("id")
	private int id;

	@JsonProperty("cast")
	private List<MovieCast> cast;

	@JsonProperty("crew")
	private List<MovieCrew> crew;

	/**
	 * Creates a new {@code MovieCasts} instance.
	 */
	public MovieCasts() {
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
	 * Gets the casts.
	 *
	 * @return the casts
	 */
	public List<MovieCast> getCast() {
		return cast;
	}

	/**
	 * Gets the crew.
	 *
	 * @return the crew members
	 */
	public List<MovieCrew> getCrew() {
		return crew;
	}
}
