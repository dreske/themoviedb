package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Dirk Reske
 */
public class MovieCrew extends CrewBase {

	private static final long serialVersionUID = -5337959098797446836L;

	@JsonProperty("name")
	private String name;

	@JsonProperty("profile_path")
	private String profilePath;

	/**
	 * Creates a new {@code MovieCrew} instance.
	 */
	public MovieCrew() {
	}

	/**
	 * Gets the name of this crew member.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the profile path of this crew member.
	 *
	 * @return the profile path
	 */
	public String getProfilePath() {
		return profilePath;
	}
}
