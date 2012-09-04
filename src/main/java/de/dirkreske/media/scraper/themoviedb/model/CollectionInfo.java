package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dirk Reske
 */
public class CollectionInfo implements Serializable {

	private static final long serialVersionUID = 4393032221743089636L;

	@JsonProperty("id")
	private int id;

	@JsonProperty("backdrop_path")
	private String backdropPath;

	@JsonProperty("name")
	private String name;

	@JsonProperty("poster_path")
	private String posterPath;

	@JsonProperty("parts")
	private List<MovieSimple> parts;

	/**
	 * Creates a new {@code CollectionInfo} instance.
	 */
	public CollectionInfo() {
	}

	/**
	 * Gets the id of the collection.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the backdrop path.
	 *
	 * @return the backdrop path
	 */
	public String getBackdropPath() {
		return backdropPath;
	}

	/**
	 * Gets the name of the collection.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the posters path.
	 *
	 * @return the posters path
	 */
	public String getPosterPath() {
		return posterPath;
	}

	/**
	 * Gets the parts of the collection.
	 *
	 * @return the parts.
	 */
	public List<MovieSimple> getParts() {
		return parts;
	}
}
