package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dirk Reske
 */
public class MovieTrailers implements Serializable {

	private static final long serialVersionUID = 3776299969846573448L;

	@JsonProperty("id")
	private int id;

	@JsonProperty("quicktime")
	private List<YoutubeTrailer> quicktimeTrailers; //TODO quicktime trailer class

	@JsonProperty("youtube")
	private List<YoutubeTrailer> youtubeTrailers;

	/**
	 * Creates a new {@code MovieTrailers} instance.
	 */
	public MovieTrailers() {
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
	 * Gets the quicktime trailer list.
	 *
	 * @return the quicktime trailers
	 */
	public List<YoutubeTrailer> getQuicktimeTrailers() {
		return quicktimeTrailers;
	}

	/**
	 * Gets the youtube trailer list.
	 *
	 * @return the youtube trailers
	 */
	public List<YoutubeTrailer> getYoutubeTrailers() {
		return youtubeTrailers;
	}
}
