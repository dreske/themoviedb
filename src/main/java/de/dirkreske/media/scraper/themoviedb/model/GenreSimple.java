package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * @author Dirk Reske
 */
public class GenreSimple implements Serializable {

	private static final long serialVersionUID = -5901449844951776172L;

	@JsonProperty("id")
	private int id;

	@JsonProperty("name")
	private String name;

	/**
	 * Creates a new {@code Genre} instance.
	 */
	public GenreSimple() {
	}

	/**
	 * The id of this genre.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * The name of this genre.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
