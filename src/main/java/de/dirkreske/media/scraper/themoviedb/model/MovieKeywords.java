package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dirk Reske
 */
public class MovieKeywords implements Serializable {

	private static final long serialVersionUID = -1592445575371334885L;

	@JsonProperty("id")
	private int id;

	@JsonProperty("keywords")
	private List<Keyword> keywords;

	/**
	 * Creates a new {@code MovieKeywords} instance.
	 */
	public MovieKeywords() {
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
	 * Gets the keyword list.
	 *
	 * @return the keywords
	 */
	public List<Keyword> getKeywords() {
		return keywords;
	}
}
