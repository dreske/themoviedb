package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dirk Reske
 */
public class AlternativeTitles implements Serializable {

	private static final long serialVersionUID = -2090436736299418877L;

	@JsonProperty("id")
	private int id;

	@JsonProperty("titles")
	private List<AlternativeTitle> alternativeTitles;

	/**
	 * Creates a new {@code AlternativeTitles} instance.
	 */
	public AlternativeTitles() {
	}

	/**
	 * Gets the id of the movie, these alternative titles are for.
	 *
	 * @return the movie's id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the alternative titles.
	 *
	 * @return the alternative titles
	 */
	public List<AlternativeTitle> getAlternativeTitles() {
		return alternativeTitles;
	}
}
