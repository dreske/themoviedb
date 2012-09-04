package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * @author Dirk Reske
 */
public class YoutubeTrailer implements Serializable {

	private static final long serialVersionUID = 8384557573183963780L;

	@JsonProperty("name")
	private String name;

	@JsonProperty("size")
	private String size;

	@JsonProperty("source")
	private String source;

	/**
	 * Creates a new {@code YoutubeTrailer} instance.
	 */
	public YoutubeTrailer() {
	}

	/**
	 * Gets the name of the trailer.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the size of the video.
	 *
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * Gets the youtube id of this trailer.
	 *
	 * @return the youtube id
	 */
	public String getSource() {
		return source;
	}
}
