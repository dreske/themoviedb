package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Dirk Reske
 */
public class CompanyDetailed extends CompanyExtended {

	private static final long serialVersionUID = -6432660887597247295L;

	@JsonProperty("description")
	private String description;

	@JsonProperty("headquarters")
	private String headquarters;

	@JsonProperty("homepage")
	private String homepage;

	@JsonProperty("parent_company")
	private CompanyExtended parentCompany;

	/**
	 * Gets the description of this company.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the headquarters of this company.
	 *
	 * @return the headquarters
	 */
	public String getHeadquarters() {
		return headquarters;
	}

	/**
	 * Gets the homepage of this company.
	 *
	 * @return the homepage
	 */
	public String getHomepage() {
		return homepage;
	}

	/**
	 * Gets the parent company of this company.
	 *
	 * @return the parent company
	 */
	public CompanyExtended getParentCompany() {
		return parentCompany;
	}
}
