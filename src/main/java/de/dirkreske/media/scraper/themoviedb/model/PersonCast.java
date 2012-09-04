package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

/**
 * @author Dirk Reske
 */
public class PersonCast extends CastBase {

	private static final long serialVersionUID = 1291900549314013790L;

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
	 * Gets the poster path of the movie.
	 *
	 * @return the movie's poster path
	 */
	public String getPosterPath() {
		return posterPath;
	}

	/**
	 * Gets the release date of the movie
	 *
	 * @return the release date
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
