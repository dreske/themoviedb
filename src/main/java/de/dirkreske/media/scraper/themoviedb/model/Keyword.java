package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * @author Dirk Reske
 */
public class Keyword implements Serializable {

	private static final long serialVersionUID = -2748304451950486968L;

	@JsonProperty("id")
	private int id;

	@JsonProperty("name")
	private String name;

	/**
	 * Creates a new {@code Keywords} instance.
	 */
	public Keyword() {
	}

	/**
	 * Gets the id of this keyword.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the name of this keyword.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
