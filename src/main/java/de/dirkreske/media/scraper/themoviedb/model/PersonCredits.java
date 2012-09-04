package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.List;


/**
 * @author Dirk Reske
 */
public class PersonCredits implements Serializable {

	private static final long serialVersionUID = -2973316448331719677L;

	@JsonProperty("id")
	private int id;

	@JsonProperty("cast")
	private List<PersonCast> cast;

	@JsonProperty("crew")
	private List<PersonCrew> crew;

	/**
	 * Gets the cast for this person.
	 *
	 * @return the cast
	 */
	public List<PersonCast> getCast() {
		return cast;
	}

	/**
	 * Gets the crew list of this person.
	 *
	 * @return the crew list
	 */
	public List<PersonCrew> getCrew() {
		return crew;
	}
}
