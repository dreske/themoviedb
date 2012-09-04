package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 * @author Dirk Reske
 */
public class PersonDetailed extends PersonSimple {

	private static final long serialVersionUID = 259995162627024850L;

	@JsonProperty("also_known_as")
	private List<String> alsoKnownAs;

	@JsonProperty("biography")
	private String biography;

	@JsonProperty("birthday")
	private Date birthday;

	@JsonProperty("deathday")
	private Date deathday;

	@JsonProperty("homepage")
	private String homepage;

	@JsonProperty("place_of_birth")
	private String placeOfBirth;

	/**
	 * Gets the list of names this person is also known as.
	 *
	 * @return the names
	 */
	public List<String> getAlsoKnownAs() {
		return alsoKnownAs;
	}

	/**
	 * Gets the biography of this person.
	 *
	 * @return the biography
	 */
	public String getBiography() {
		return biography;
	}

	/**
	 * Gets the birthday of this person.
	 *
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * Gets the deathday of this person.
	 *
	 * @return the deathday
	 */
	public Date getDeathday() {
		return deathday;
	}

	/**
	 * Gets the homepage of this person.
	 *
	 * @return the homepage
	 */
	public String getHomepage() {
		return homepage;
	}

	/**
	 * Gets the place this person is born.
	 *
	 * @return the place of birth
	 */
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
}
