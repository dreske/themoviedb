package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * @author Dirk Reske
 */
public class CollectionSimple implements Serializable {

	private static final long serialVersionUID = -119547971920114844L;

	@JsonProperty("id")
	private int id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("backdrop_path")
	private String backdropPath;

	@JsonProperty("poster_path")
	private String posterPath;

	/**
	 * Gets the id of this collection.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the name of this collection.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the backdrop path of this collection.
	 *
	 * @return the backdrop path
	 */
	public String getBackdropPath() {
		return backdropPath;
	}

	/**
	 * Gets the poster path of this collection.
	 *
	 * @return the poster path
	 */
	public String getPosterPath() {
		return posterPath;
	}
}
