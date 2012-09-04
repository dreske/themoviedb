package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * @author Dirk Reske
 */
public class CompanySimple implements Serializable {

	private static final long serialVersionUID = 2363278158412489319L;

	@JsonProperty("id")
	private int id;

	@JsonProperty("name")
	private String name;

	/**
	 * Creates a new {@code CompanySimple} instance.
	 */
	public CompanySimple() {
	}

	/**
	 * The id of this company.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * The name of this company.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
