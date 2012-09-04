package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dirk Reske
 */
public class ReleaseInfos implements Serializable {

	private static final long serialVersionUID = 1767446060680786045L;

	@JsonProperty("id")
	private int id;

	@JsonProperty("countries")
	private List<ReleaseInfo> releaseInfos;

	/**
	 * Creates a new {@code ReleaseInfos} instance.
	 */
	public ReleaseInfos() {
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
	 * Gets the list of release infos.
	 *
	 * @return the release infos
	 */
	public List<ReleaseInfo> getReleaseInfos() {
		return releaseInfos;
	}
}
