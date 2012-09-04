package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * @author Dirk Reske
 */
public class Image implements Serializable {

	private static final long serialVersionUID = 1350526980673118271L;

	@JsonProperty("aspect_ratio")
	private double aspectRatio;

	@JsonProperty("file_path")
	private String filePath;

	@JsonProperty("height")
	private int height;

	@JsonProperty("width")
	private int width;

	@JsonProperty("iso_639_1")
	private String iso6391;

	@JsonProperty("vote_average")
	private double voteAverage;

	@JsonProperty("vote_count")
	private int voteCount;

	/**
	 * Creates a new {@code Image} instance.
	 */
	public Image() {
	}

	/**
	 * Gets the aspect ratio of this image.
	 *
	 * @return the aspect ration
	 */
	public double getAspectRatio() {
		return aspectRatio;
	}

	/**
	 * Gets the file path of this image.
	 *
	 * @return the file path
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * Gets the height of this image.
	 *
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Gets the width of this image.
	 *
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Gets the iso 639-1 code of the language of this image.
	 *
	 * @return the language code
	 */
	public String getIso6391() {
		return iso6391;
	}

	/**
	 * Gets the average voting result
	 *
	 * @return the average voting
	 */
	public double getVoteAverage() {
		return voteAverage;
	}

	/**
	 * Gets the count of votes for this image.
	 *
	 * @return the vote count
	 */
	public int getVoteCount() {
		return voteCount;
	}
}
