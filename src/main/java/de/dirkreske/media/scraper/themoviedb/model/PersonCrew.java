package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

/**
 * @author Dirk Reske
 */
public class PersonCrew extends CrewBase {

	private static final long serialVersionUID = 301734030237773274L;

	@JsonProperty("title")
	private String title;

	@JsonProperty("original_title")
	private String originalTitle;

	@JsonProperty("poster_path")
	private String posterPath;

	@JsonProperty("release_date")
	private Date releaseDate;

	@JsonProperty("adult")
	private boolean adult;

	/**
	 * Gets the title of the movie.
	 *
	 * @return the movie's title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets the original title of the movie.
	 *
	 * @return the movie's original title
	 */
	public String getOriginalTitle() {
		return originalTitle;
	}

	/**
	 * Gets the path of the movie poster.
	 *
	 * @return the poster path
	 */
	public String getPosterPath() {
		return posterPath;
	}

	/**
	 * Gets the release date of the movie.
	 *
	 * @return the movie's release date
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}

	/**
	 * Indicates whether the movie is classified for adults.
	 *
	 * @return true or false
	 */
	public boolean isAdult() {
		return adult;
	}
}
