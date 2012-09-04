package de.dirkreske.media.scraper.themoviedb.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * @author Dirk Reske
 */
public class MovieDetailed extends MovieExtended {

	private static final long serialVersionUID = -3177770237298251784L;

	@JsonProperty("belongs_to_collection")
	private CollectionSimple belongsToCollection;

	@JsonProperty("budget")
	private String budget;

	@JsonProperty("genres")
	private List<GenreSimple> genres;

	@JsonProperty("homepage")
	private String homepage;

	@JsonProperty("imdb_id")
	private String imdbId;

	@JsonProperty("overview")
	private String overview;

	@JsonProperty("production_companies")
	private List<CompanySimple> productionCompanies;

	@JsonProperty("production_countries")
	private List<Country> productionCountries;

	@JsonProperty("revenue")
	private String revenue;

	@JsonProperty("runtime")
	private int runtime;

	@JsonProperty("spoken_languages")
	private List<Language> spokenLanguages;

	@JsonProperty("tagline")
	private String tagline;

	/**
	 * Creates a new {@code MovieDetailed} instance.
	 */
	public MovieDetailed() {
	}

	/**
	 * Gets the collection this movie belongs to.
	 *
	 * @return the collection or null, if this movie does not belongs to a collection
	 */
	public CollectionSimple getBelongsToCollection() {
		return belongsToCollection;
	}

	/**
	 * Gets the movies budget.
	 *
	 * @return the budget
	 */
	public String getBudget() {
		return budget;
	}

	/**
	 * Gets the movies genres.
	 *
	 * @return the genres
	 */
	public List<GenreSimple> getGenres() {
		return genres;
	}

	/**
	 * Gets the movies homepage.
	 *
	 * @return the homepage
	 */
	public String getHomepage() {
		return homepage;
	}

	/**
	 * Gets the movies imdb id.
	 *
	 * @return the imdb id
	 */
	public String getImdbId() {
		return imdbId;
	}

	/**
	 * Gets a overview of this movie.
	 *
	 * @return the overview
	 */
	public String getOverview() {
		return overview;
	}

	/**
	 * Gets the production companies of this movie.
	 *
	 * @return the production companies
	 */
	public List<CompanySimple> getProductionCompanies() {
		return productionCompanies;
	}

	/**
	 * Gets the production countries of this movie.
	 *
	 * @return the production countries
	 */
	public List<Country> getProductionCountries() {
		return productionCountries;
	}

	/**
	 * Gets the revenue of this movie.
	 *
	 * @return the revenue
	 */
	public String getRevenue() {
		return revenue;
	}

	/**
	 * Gets the runtime of this movie.
	 *
	 * @return the runtime in minutes
	 */
	public int getRuntime() {
		return runtime;
	}

	/**
	 * Gets the spoken languages of this movie.
	 *
	 * @return the languages
	 */
	public List<Language> getSpokenLanguages() {
		return spokenLanguages;
	}

	/**
	 * Gets the tagline of this movie.
	 *
	 * @return the tagline
	 */
	public String getTagline() {
		return tagline;
	}
}
