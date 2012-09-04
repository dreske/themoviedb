package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * @author Dirk Reske
 */
public abstract class CrewBase implements Serializable {

	private static final long serialVersionUID = -7386884484854653030L;

	@JsonProperty("department")
	private String department;

	@JsonProperty("job")
	private String job;

	@JsonProperty("id")
	private int id;

	/**
	 * Creates a new {@code CrewBase} instance.
	 */
	protected CrewBase() {
	}

	/**
	 * Gets the department of this crew member.
	 *
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * Gets the job of this crew member.
	 *
	 * @return the job
	 */
	public String getJob() {
		return job;
	}

	/**
	 * Gets the id of this crew member.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
}
