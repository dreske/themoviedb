package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dirk Reske
 */
public class MovieTranslations implements Serializable {

	private static final long serialVersionUID = 8847013137231443312L;

	@JsonProperty("id")
	private int id;

	@JsonProperty("translations")
	private List<Translation> translations;

	/**
	 * Creates a new {@code MovieTranslations} instance.
	 */
	public MovieTranslations() {
	}

	/**
	 * The id of the associated movie.
	 *
	 * @return the movie's id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the translation list.
	 *
	 * @return the translations
	 */
	public List<Translation> getTranslations() {
		return translations;
	}
}
