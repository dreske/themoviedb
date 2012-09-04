package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * @author Dirk Reske
 */
public abstract class CastBase implements Serializable {

	private static final long serialVersionUID = 7718018940455848518L;

	@JsonProperty("character")
	private String character;

	@JsonProperty("id")
	private int id;

	/**
	 * Creates a new {@code CastBase} instance.
	 */
	protected CastBase() {
	}

	/**
	 * Gets the character.
	 *
	 * @return the character
	 */
	public String getCharacter() {
		return character;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}
