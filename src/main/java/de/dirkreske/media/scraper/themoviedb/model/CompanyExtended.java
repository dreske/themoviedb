package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Dirk Reske
 */
public class CompanyExtended extends CompanySimple {

	private static final long serialVersionUID = -312555787455303059L;

	@JsonProperty("logo_path")
	private String logoPath;

	/**
	 * Creates a new {@code CompanyExtended} instance.
	 */
	public CompanyExtended() {
	}

	/**
	 * Gets the logo path of this company.
	 *
	 * @return the logo path
	 */
	public String getLogoPath() {
		return logoPath;
	}
}
