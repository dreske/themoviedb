package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Encapsulates paginated result sets.
 *
 * @param <T> the encapsulated object type
 * @author Dirk Reske
 */
public class PaginatedResult<T> implements Serializable {

	private static final long serialVersionUID = 4955147686999339947L;

	@JsonProperty("page")
	private int page;

	@JsonProperty("total_pages")
	private int totalPages;

	@JsonProperty("total_results")
	private int totalResults;

	@JsonProperty("results")
	private List<T> results;

	/**
	 * Creates a new {@code PaginatedResult} instance.
	 */
	public PaginatedResult() {
	}

	/**
	 * Gets the current page.
	 *
	 * @return the current page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * Gets the total number of pages.
	 *
	 * @return the number of pages
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * Gets the total count of results.
	 *
	 * @return the count of results
	 */
	public int getTotalResults() {
		return totalResults;
	}

	/**
	 * Gets the results of the current page.
	 *
	 * @return the results
	 */
	public List<T> getResults() {
		return results;
	}
}
