package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * @author Dirk Reske
 */
public class Configuration implements Serializable {

	private static final long serialVersionUID = -8639108616209689469L;

	@JsonProperty("images")
	private ImageConfiguration imageConfiguration;

	/**
	 * Gets the image configuration.
	 *
	 * @return the image configuration
	 */
	public ImageConfiguration getImageConfiguration() {
		return imageConfiguration;
	}
}
