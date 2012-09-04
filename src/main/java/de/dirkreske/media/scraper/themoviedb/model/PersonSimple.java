package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * @author Dirk Reske
 */
public class PersonSimple implements Serializable {

	private static final long serialVersionUID = -5318866679708304279L;

	@JsonProperty("id")
	private int id;

	@JsonProperty("adult")
	private boolean adult;


	@JsonProperty("profile_path")
	private String profilePath;

	@JsonProperty("name")
	private String name;

	/**
	 * Gets the id of this person.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Indicates whether this person is classified for adults.
	 *
	 * @return true if this is classified for adults; false if not
	 */
	public boolean isAdult() {
		return adult;
	}

	/**
	 * Gets the profile path of this person.
	 *
	 * @return the profile path
	 */
	public String getProfilePath() {
		return profilePath;
	}

	/**
	 * Gets the name of this person.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
