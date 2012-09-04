package de.dirkreske.media.scraper.themoviedb;

import de.dirkreske.media.scraper.themoviedb.exception.NotAuthorizedException;
import de.dirkreske.media.scraper.themoviedb.exception.NotFoundException;
import de.dirkreske.media.scraper.themoviedb.model.AccountInfo;
import de.dirkreske.media.scraper.themoviedb.model.AlternativeTitle;
import de.dirkreske.media.scraper.themoviedb.model.CollectionInfo;
import de.dirkreske.media.scraper.themoviedb.model.CompanyDetailed;
import de.dirkreske.media.scraper.themoviedb.model.Configuration;
import de.dirkreske.media.scraper.themoviedb.model.GenreSimple;
import de.dirkreske.media.scraper.themoviedb.model.MovieCasts;
import de.dirkreske.media.scraper.themoviedb.model.MovieDetailed;
import de.dirkreske.media.scraper.themoviedb.model.MovieExtended;
import de.dirkreske.media.scraper.themoviedb.model.MovieImages;
import de.dirkreske.media.scraper.themoviedb.model.MovieKeywords;
import de.dirkreske.media.scraper.themoviedb.model.MovieTrailers;
import de.dirkreske.media.scraper.themoviedb.model.PaginatedResult;
import de.dirkreske.media.scraper.themoviedb.model.PersonCredits;
import de.dirkreske.media.scraper.themoviedb.model.PersonDetailed;
import de.dirkreske.media.scraper.themoviedb.model.PersonSimple;
import de.dirkreske.media.scraper.themoviedb.model.RatedMovie;
import de.dirkreske.media.scraper.themoviedb.model.ReleaseInfo;
import de.dirkreske.media.scraper.themoviedb.model.RequestToken;
import de.dirkreske.media.scraper.themoviedb.model.SessionId;
import de.dirkreske.media.scraper.themoviedb.model.StatusResponse;
import de.dirkreske.media.scraper.themoviedb.model.Translation;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.ClientResponseFailure;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * @author Dirk Reske
 */
public class TheMovieDB {
	private static final Logger LOG = LoggerFactory.getLogger(TheMovieDB.class);

	private static final String BASE_URL = "http://api.themoviedb.org/";
	private static final String CONFIG_API_KEY = "api.key";
	private static final String CONFIG_DEFAULT_LANGUAGE = "api.lang.default";

	private String apiKey;
	private String sessionId;
	private Integer accountId;
	private String defaultLanguage;

	private final TmdbServiceAPI serviceAPI;

	static {
		RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
	}

	/**
	 * Creates a new {@code TheMovieDB} client.
	 */
	public TheMovieDB() {
		serviceAPI = ProxyFactory.create(TmdbServiceAPI.class, BASE_URL);

		//Load configuration
		try {
			Properties config = new Properties();
			config.load(TheMovieDB.class.getResourceAsStream("/tmdb.properties"));

			setApiKey(config.getProperty(CONFIG_API_KEY));
			setDefaultLanguage(config.getProperty(CONFIG_DEFAULT_LANGUAGE, "en"));
		} catch (IOException e) {
			LOG.warn("Could not load configuration file tmdb.properties", e);
		}
	}

	/**
	 * This method is used to generate a valid request token for user based authentication. A request token is required in order to request a session id.
	 * <p>
	 * You can generate any number of request tokens but they will expire after 60 minutes. As soon as a valid session id has been created the token will be destroyed.
	 * </p>
	 *
	 * @return the request token
	 */
	public AuthenticationRequest createAuthenticationRequest() {
		ClientResponse<RequestToken> response = serviceAPI.createRequestToken(apiKey);
		RequestToken requestToken = response.getEntity();

		return new AuthenticationRequest(requestToken.getRequestToken(),
				response.getHeaders().getFirst("Authentication-Callback"));
	}

	/**
	 * This method is used to generate a session id for user based authentication. A session id is required in order to use any of the write methods.
	 *
	 * @param authenticationRequest an authenticated authentication request
	 * @return the session id
	 */
	public String finishAuthentication(AuthenticationRequest authenticationRequest) {
		try {
			SessionId sessionId = serviceAPI.createSessionId(apiKey, authenticationRequest.getRequestToken());
			this.sessionId = sessionId.getSessionId();
			return this.sessionId;
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	/**
	 * This method allows you to retrieve the account ID along with some basic account information.
	 * <p/>
	 * The account ID is required to use any of the more specific account methods like grabbing a list of rated or favourite movies.
	 * A users password is never exchanged.
	 *
	 * @return the account infos
	 */
	public AccountInfo getAccountInfo() {
		LOG.debug("getAccountInfo");
		try {
			return serviceAPI.getAccountInfo(apiKey, sessionId);
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	/**
	 * Searches for movies.
	 *
	 * @param query The query param is your search text. It works best when the text has been properly escaped.
	 *              Hint, you can append a year to narrow your results.
	 * @return the search results
	 */
	public PaginatedResult<MovieExtended> searchMovies(String query) {
		return searchMovies(query, 1);
	}

	/**
	 * Searches for movies.
	 *
	 * @param query The query param is your search text. It works best when the text has been properly escaped.
	 *              Hint, you can append a year to narrow your results.
	 * @param page  Some searches will have more than 20 results, the default number of items returned per page.
	 *              To iterate through use the page parameter.
	 * @return the search results
	 */
	public PaginatedResult<MovieExtended> searchMovies(String query, int page) {
		return searchMovies(query, page, null);
	}

	/**
	 * Searches for movies.
	 *
	 * @param query    The query param is your search text. It works best when the text has been properly escaped.
	 *                 Hint, you can append a year to narrow your results.
	 * @param page     Some searches will have more than 20 results, the default number of items returned per page.
	 *                 To iterate through use the page parameter.
	 * @param language It’s important to note that the language parameter acts as a filter for the title and poster field.
	 *                 This is to say, if you make a request for the German language,
	 *                 the original_title will always be returned but the title field will be empty if it hasn’t been translated.
	 *                 For posters, we’ll serve the German poster if available, otherwise default back to English.
	 *                 The expected value is a ISO 639-1 code.
	 * @return the search results
	 */
	public PaginatedResult<MovieExtended> searchMovies(String query, int page, String language) {
		return searchMovies(query, page, language, false);
	}

	/**
	 * Searches for movies.
	 *
	 * @param query        The query param is your search text. It works best when the text has been properly escaped.
	 *                     Hint, you can append a year to narrow your results.
	 * @param page         Some searches will have more than 20 results, the default number of items returned per page.
	 *                     To iterate through use the page parameter.
	 * @param language     It’s important to note that the language parameter acts as a filter for the title and poster field.
	 *                     This is to say, if you make a request for the German language,
	 *                     the original_title will always be returned but the title field will be empty if it hasn’t been translated. For posters, we’ll serve the German poster if available, otherwise default back to English. The expected value is a ISO 639-1 code.
	 * @param includeAdult You can toggle whether or not to include adult items in your search by using this parameter.
	 *                     The expected value is either true or false. When it is not specified, it is set to false.
	 * @return the search results
	 */
	public PaginatedResult<MovieExtended> searchMovies(String query, int page, String language, boolean includeAdult) {
		if (page < 1) {
			throw new IllegalArgumentException("page must be greater or equal to one");
		}

		if (language == null) {
			language = defaultLanguage;
		}
		LOG.debug("searchMovies: searchMovies={}, page={}", query, page);

		return serviceAPI.searchMovies(apiKey, query, page, language, includeAdult);
	}

	/**
	 * This method is used to retrieve all of the basic movie information.
	 * It will return the single highest rated poster and backdrop.
	 *
	 * @param movieId the movies id
	 * @return the movie details
	 */
	public MovieDetailed getMovieInfo(int movieId) {
		return getMovieInfo(movieId, null);
	}

	/**
	 * This method is used to retrieve the newest movie that was added to TMDb.
	 *
	 * @return the latest movie info
	 */
	public MovieDetailed getLatestMovie() {
		LOG.debug("getLatestMovie");
		try {
			return serviceAPI.getLatestMovie(apiKey);
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	/**
	 * This method is used to retrieve all of the basic movie information.
	 * It will return the single highest rated poster and backdrop.
	 *
	 * @param movieId  the movies id
	 * @param language The language parameter tries to localize the movie data we return.
	 *                 If the language being requested does not exist, the value will be left blank.
	 *                 The expected value is a ISO 639-1 code.
	 * @return the movie details
	 */
	public MovieDetailed getMovieInfo(int movieId, String language) {
		if (language == null) {
			language = defaultLanguage;
		}
		LOG.debug("getMovieInfo: movieId={}, language={}", movieId, language);

		try {
			return serviceAPI.getMovie(apiKey, movieId, language);
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	/**
	 * This method is used to retrieve all of the movie cast information.
	 * The results are split into separate cast and crew arrays.
	 *
	 * @param movieId the id of the movie
	 * @return the casts
	 */
	public MovieCasts getMovieCasts(int movieId) {
		LOG.debug("getMovieCasts: movieId={}", movieId);
		try {
			return serviceAPI.getMovieCasts(apiKey, movieId);
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	/**
	 * This method should be used when you’re wanting to retrieve all of the images for a particular movie.
	 *
	 * @param movieId the movies id
	 * @return the movie images
	 */
	public MovieImages getMovieImages(int movieId) {
		return getMovieImages(movieId, null);
	}

	/**
	 * This method should be used when you’re wanting to retrieve all of the images for a particular movie.
	 *
	 * @param movieId  the movies id
	 * @param language It’s important to note that the language parameter acts as a filter.
	 *                 This is to say, if you make a request for the German language, you will only get the images tagged as German back. If you want everything, simply omit the language parameter all together.
	 *                 The expected value is a ISO 639-1 code.
	 * @return the movie images
	 */
	public MovieImages getMovieImages(int movieId, String language) {
		if (language == null) {
			language = defaultLanguage;
		}
		LOG.debug("getMovieImages: movieId={}, language={}", movieId, language);

		try {
			return serviceAPI.getMovieImages(apiKey, movieId, language);
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	/**
	 * This method is used to retrieve all of the keywords that have been added to a particular movie. Currently, only English keywords exist.
	 *
	 * @param movieId the id of the movie
	 * @return the keywords
	 */
	public MovieKeywords getMovieKeywords(int movieId) {
		LOG.debug("getMovieKeywords: movieId={}", movieId);

		try {
			return serviceAPI.getMovieKeywords(apiKey, movieId);
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	/**
	 * This method is used to retrieve a list of the available translations for a specific movie.
	 *
	 * @param movieId the id of the movie
	 * @return the available translations
	 */
	public List<Translation> getMovieTranslations(int movieId) {
		LOG.debug("getMovieTranslations: movieId={}", movieId);
		try {
			return serviceAPI.getMovieTranslations(apiKey, movieId).getTranslations();
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	/**
	 * This method is used to retrieve all of the alternative titles we have for a particular movie.
	 *
	 * @param movieId the id of the movie
	 * @return the alternative titles
	 */
	public List<AlternativeTitle> getAlternativeTitles(int movieId) {
		return getAlternativeTitles(movieId, null);
	}

	/**
	 * This method is used to retrieve all of the alternative titles we have for a particular movie.
	 *
	 * @param movieId the id of the movie
	 * @param country The country parameter will filter the results to only include titles in that particular country.
	 *                The expected value is a ISO 3166-1 code.
	 * @return the alternative titles
	 */
	public List<AlternativeTitle> getAlternativeTitles(int movieId, String country) {
		LOG.debug("getAlternativeTitles: movieId={}, country={}", movieId, country);
		try {
			return serviceAPI.getAlternativeTitles(apiKey, movieId, country).getAlternativeTitles();
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	/**
	 * The similar movies method will let you retrieve the similar movies for a particular movie.
	 * This data is created dynamically but with the help of users votes on TMDb.
	 * The data is much better with movies that have more keywords.
	 *
	 * @param movieId the movie id
	 * @return the now playing movies
	 */
	public PaginatedResult<MovieExtended> getSimilarMovies(int movieId) {
		return getSimilarMovies(movieId, 1, null);
	}

	/**
	 * The similar movies method will let you retrieve the similar movies for a particular movie.
	 * This data is created dynamically but with the help of users votes on TMDb.
	 * The data is much better with movies that have more keywords.
	 *
	 * @param movieId the movie id
	 * @param page    This method will try likely have more than 20 results, the default number of items returned per page.
	 *                To iterate through use the page parameter.
	 * @return the now playing movies
	 */
	public PaginatedResult<MovieExtended> getSimilarMovies(int movieId, int page) {
		return getSimilarMovies(movieId, page, null);
	}

	/**
	 * The similar movies method will let you retrieve the similar movies for a particular movie.
	 * This data is created dynamically but with the help of users votes on TMDb.
	 * The data is much better with movies that have more keywords.
	 *
	 * @param movieId  the movie id
	 * @param language It’s important to note that the language parameter acts as a filter for the title and poster field.
	 *                 This is to say, if you make a request for the German language,
	 *                 the original_title will always be returned but the title field will be empty if it hasn’t been translated.
	 *                 For posters, we’ll serve the German poster if available, otherwise default back to English.
	 *                 The expected value is a ISO 639-1 code.
	 * @return the now playing movies
	 */
	public PaginatedResult<MovieExtended> getSimilarMovies(int movieId, String language) {
		return getSimilarMovies(movieId, 1, language);
	}

	/**
	 * The similar movies method will let you retrieve the similar movies for a particular movie.
	 * This data is created dynamically but with the help of users votes on TMDb.
	 * The data is much better with movies that have more keywords.
	 *
	 * @param movieId  the movie id
	 * @param page     This method will try likely have more than 20 results, the default number of items returned per page.
	 *                 To iterate through use the page parameter.
	 * @param language It’s important to note that the language parameter acts as a filter for the title and poster field.
	 *                 This is to say, if you make a request for the German language,
	 *                 the original_title will always be returned but the title field will be empty if it hasn’t been translated.
	 *                 For posters, we’ll serve the German poster if available, otherwise default back to English.
	 *                 The expected value is a ISO 639-1 code.
	 * @return the now playing movies
	 */
	public PaginatedResult<MovieExtended> getSimilarMovies(int movieId, int page, String language) {
		if (page < 1) {
			throw new IllegalArgumentException("page must be greater or equal to one");
		}

		if (language == null) {
			language = defaultLanguage;
		}
		LOG.debug("getSimilarMovies: movieId={}, page={}, language={}", new Object[]{movieId, page, language});
		try {
			return serviceAPI.getSimilarMovies(apiKey, movieId, page, language);
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	/**
	 * Adds the given movie to the favorites.
	 *
	 * @param movieId the id of the movie to add
	 * @return the status response
	 */
	public StatusResponse addFavorite(int movieId) {
		LOG.debug("addFavorite: movieId={}", movieId);
		try {
			TmdbServiceAPI.AccountAddFavoriteRequest request = new TmdbServiceAPI.AccountAddFavoriteRequest(movieId, true);
			return serviceAPI.accountAddFavorite(apiKey, sessionId, movieId, request);
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	/**
	 * Removes the given movie from the favorites.
	 *
	 * @param movieId the id of the movie to remove
	 * @return the status response
	 */
	public StatusResponse removeFavorite(int movieId) {
		LOG.debug("removeFavorite: movieId={}", movieId);
		try {
			TmdbServiceAPI.AccountAddFavoriteRequest request = new TmdbServiceAPI.AccountAddFavoriteRequest(movieId, false);
			return serviceAPI.accountAddFavorite(apiKey, sessionId, movieId, request);
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	/**
	 * This method allows you to retrieve the favourite movies for a particular account.
	 *
	 * @return the favorite movies
	 */
	public PaginatedResult<MovieExtended> getFavoriteMovies() {
		return getFavoriteMovies(1, null);
	}

	/**
	 * This method allows you to retrieve the favourite movies for a particular account.
	 *
	 * @param page This method will try likely have more than 20 results, the default number of items returned per page.
	 *             To iterate through use the page parameter.
	 * @return the favorite movies
	 */
	public PaginatedResult<MovieExtended> getFavoriteMovies(int page) {
		return getFavoriteMovies(page, null);
	}

	/**
	 * This method allows you to retrieve the favourite movies for a particular account.
	 *
	 * @param language It’s important to note that the language parameter acts as a filter for the title and poster field.
	 *                 This is to say, if you make a request for the German language,
	 *                 the original_title will always be returned but the title field will be empty if it hasn’t been translated.
	 *                 For posters, we’ll serve the German poster if available, otherwise default back to English.
	 *                 The expected value is a ISO 639-1 code.
	 * @return the favorite movies
	 */
	public PaginatedResult<MovieExtended> getFavoriteMovies(String language) {
		return getFavoriteMovies(1, language);
	}

	/**
	 * This method allows you to retrieve the favourite movies for a particular account.
	 *
	 * @param page     This method will try likely have more than 20 results, the default number of items returned per page.
	 *                 To iterate through use the page parameter.
	 * @param language It’s important to note that the language parameter acts as a filter for the title and poster field.
	 *                 This is to say, if you make a request for the German language,
	 *                 the original_title will always be returned but the title field will be empty if it hasn’t been translated.
	 *                 For posters, we’ll serve the German poster if available, otherwise default back to English.
	 *                 The expected value is a ISO 639-1 code.
	 * @return the favorite movies
	 */
	public PaginatedResult<MovieExtended> getFavoriteMovies(int page, String language) {
		if (page < 1) {
			throw new IllegalArgumentException("page must be greater or equal to one");
		}

		if (language == null) {
			language = defaultLanguage;
		}

		LOG.debug("getFavoriteMovies: page={}, language={}", page, language);
		try {
			return serviceAPI.getAccountFavoriteMovies(apiKey, sessionId, getAccountId(), page, language);
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	/**
	 * This method allows you to retrieve the rated movies for a particular account.
	 *
	 * @return the rated movies
	 */
	public PaginatedResult<RatedMovie> getRatedMovies() {
		return getRatedMovies(1, null);
	}

	/**
	 * This method allows you to retrieve the rated movies for a particular account.
	 *
	 * @param page This method will try likely have more than 20 results, the default number of items returned per page.
	 *             To iterate through use the page parameter.
	 * @return the rated movies
	 */
	public PaginatedResult<RatedMovie> getRatedMovies(int page) {
		return getRatedMovies(page, null);
	}

	/**
	 * This method allows you to retrieve the rated movies for a particular account.
	 *
	 * @param language It’s important to note that the language parameter acts as a filter for the title and poster field.
	 *                 This is to say, if you make a request for the German language,
	 *                 the original_title will always be returned but the title field will be empty if it hasn’t been translated.
	 *                 For posters, we’ll serve the German poster if available, otherwise default back to English.
	 *                 The expected value is a ISO 639-1 code.
	 * @return the rated movies
	 */
	public PaginatedResult<RatedMovie> getRatedMovies(String language) {
		return getRatedMovies(1, language);
	}

	/**
	 * This method allows you to retrieve the rated movies for a particular account.
	 *
	 * @param page     This method will try likely have more than 20 results, the default number of items returned per page.
	 *                 To iterate through use the page parameter.
	 * @param language It’s important to note that the language parameter acts as a filter for the title and poster field.
	 *                 This is to say, if you make a request for the German language,
	 *                 the original_title will always be returned but the title field will be empty if it hasn’t been translated.
	 *                 For posters, we’ll serve the German poster if available, otherwise default back to English.
	 *                 The expected value is a ISO 639-1 code.
	 * @return the rated movies
	 */
	public PaginatedResult<RatedMovie> getRatedMovies(int page, String language) {
		if (page < 1) {
			throw new IllegalArgumentException("page must be greater or equal to one");
		}

		if (language == null) {
			language = defaultLanguage;
		}

		LOG.debug("getRatedMovies: page={}, language={}", page, language);
		try {
			return serviceAPI.getAccountRatedMovies(apiKey, sessionId, getAccountId(), page, language);
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	/**
	 * You can use this method to retrieve the list of genres used on TMDb.
	 * These ids will correspond to those found in movie calls.
	 *
	 * @return the genres list
	 */
	public List<GenreSimple> getGenres() {
		return getGenres(null);
	}

	/**
	 * You can use this method to retrieve the list of genres used on TMDb.
	 * These ids will correspond to those found in movie calls.
	 *
	 * @param language the langage as iso 639-1 code
	 * @return the genres list
	 */
	public List<GenreSimple> getGenres(String language) {
		if (language == null) {
			language = defaultLanguage;
		}

		LOG.debug("getGenres: language={}", language);
		try {
			return serviceAPI.getGenres(apiKey, language).getGenres();
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	/**
	 * If you're looking to get a list of movies per genre, you can look them up with this method.
	 * It is important to understand that only movies with more than 10 votes get listed.
	 * This prevents movies from 1 10/10 rating from being listed first and for the first 5 pages.
	 *
	 * @param genreId the genre's id
	 * @return the favorite movies
	 */
	public PaginatedResult<MovieExtended> getGenreMovies(int genreId) {
		return getGenreMovies(genreId, 1, null);
	}

	/**
	 * If you're looking to get a list of movies per genre, you can look them up with this method.
	 * It is important to understand that only movies with more than 10 votes get listed.
	 * This prevents movies from 1 10/10 rating from being listed first and for the first 5 pages.
	 *
	 * @param genreId the genre's id
	 * @param page    This method will try likely have more than 20 results, the default number of items returned per page.
	 *                To iterate through use the page parameter.
	 * @return the favorite movies
	 */
	public PaginatedResult<MovieExtended> getGenreMovies(int genreId, int page) {
		return getGenreMovies(genreId, page, null);
	}

	/**
	 * If you're looking to get a list of movies per genre, you can look them up with this method.
	 * It is important to understand that only movies with more than 10 votes get listed.
	 * This prevents movies from 1 10/10 rating from being listed first and for the first 5 pages.
	 *
	 * @param genreId  the genre's id
	 * @param language It’s important to note that the language parameter acts as a filter for the title and poster field.
	 *                 This is to say, if you make a request for the German language,
	 *                 the original_title will always be returned but the title field will be empty if it hasn’t been translated.
	 *                 For posters, we’ll serve the German poster if available, otherwise default back to English.
	 *                 The expected value is a ISO 639-1 code.
	 * @return the favorite movies
	 */
	public PaginatedResult<MovieExtended> getGenreMovies(int genreId, String language) {
		return getGenreMovies(genreId, 1, language);
	}

	/**
	 * If you're looking to get a list of movies per genre, you can look them up with this method.
	 * It is important to understand that only movies with more than 10 votes get listed.
	 * This prevents movies from 1 10/10 rating from being listed first and for the first 5 pages.
	 *
	 * @param genreId  the genre's id
	 * @param page     This method will try likely have more than 20 results, the default number of items returned per page.
	 *                 To iterate through use the page parameter.
	 * @param language It’s important to note that the language parameter acts as a filter for the title and poster field.
	 *                 This is to say, if you make a request for the German language,
	 *                 the original_title will always be returned but the title field will be empty if it hasn’t been translated.
	 *                 For posters, we’ll serve the German poster if available, otherwise default back to English.
	 *                 The expected value is a ISO 639-1 code.
	 * @return the favorite movies
	 */
	public PaginatedResult<MovieExtended> getGenreMovies(int genreId, int page, String language) {
		if (page < 1) {
			throw new IllegalArgumentException("page must be greater or equal to one");
		}

		if (language == null) {
			language = defaultLanguage;
		}

		LOG.debug("getGenreMovies: page={}, language={}", page, language);
		try {
			return serviceAPI.getGenreMovies(apiKey, genreId, page, language);
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	/**
	 * This method is used to retrieve all of the release and certification data we have for a specific movie.
	 *
	 * @param movieId the if of the movie
	 * @return the release infos
	 */
	public List<ReleaseInfo> getReleaseInfos(int movieId) {
		LOG.debug("getReleaseInfos: movieId={}", movieId);
		try {
			return serviceAPI.getReleaseInfos(apiKey, movieId).getReleaseInfos();
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	/**
	 * This method is used to retrieve all of the trailers for a particular movie.
	 * Supported sites are YouTube and QuickTime.
	 *
	 * @param movieId the movies id
	 * @return the trailers
	 */
	public MovieTrailers getMovieTrailers(int movieId) {
		return getMovieTrailers(movieId, null);
	}

	/**
	 * This method is used to retrieve all of the trailers for a particular movie.
	 * Supported sites are YouTube and QuickTime.
	 *
	 * @param movieId  the movies id
	 * @param language The language parameter tries to localize the movie data we return.
	 *                 If the language being requested does not exist, the value will be left blank.
	 *                 The expected value is a ISO 639-1 code.
	 * @return the trailers
	 */
	public MovieTrailers getMovieTrailers(int movieId, String language) {
		if (language == null) {
			language = defaultLanguage;
		}

		LOG.debug("getMovieTrailers: movieId={}, language={}", movieId, language);
		try {
			return serviceAPI.getMovieTrailers(apiKey, movieId, language);
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	/**
	 * The now playing movie method will let you retrieve the movies currently in theatres.
	 *
	 * @return the now playing movies
	 */
	public PaginatedResult<MovieExtended> getNowPlayingMovies() {
		return getNowPlayingMovies(1);
	}

	/**
	 * The now playing movie method will let you retrieve the movies currently in theatres.
	 *
	 * @param page This method will try likely have more than 20 results, the default number of items returned per page. To iterate through use the page parameter.
	 * @return the now playing movies
	 */
	public PaginatedResult<MovieExtended> getNowPlayingMovies(int page) {
		return getNowPlayingMovies(page, null);
	}

	/**
	 * The now playing movie method will let you retrieve the movies currently in theatres.
	 *
	 * @param page     This method will try likely have more than 20 results, the default number of items returned per page.
	 *                 To iterate through use the page parameter.
	 * @param language It’s important to note that the language parameter acts as a filter for the title and poster field.
	 *                 This is to say, if you make a request for the German language,
	 *                 the original_title will always be returned but the title field will be empty if it hasn’t been translated.
	 *                 For posters, we’ll serve the German poster if available, otherwise default back to English.
	 *                 The expected value is a ISO 639-1 code.
	 * @return the now playing movies
	 */
	public PaginatedResult<MovieExtended> getNowPlayingMovies(int page, String language) {
		if (page < 1) {
			throw new IllegalArgumentException("page must be greater or equal to one");
		}

		if (language == null) {
			language = defaultLanguage;
		}
		LOG.debug("getNowPlayingMovies: page={}, language={}", page, language);
		try {
			return serviceAPI.getNowPlayingMovies(apiKey, page, language);
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	/**
	 * The top rated movie method will let you retrieve the top rated movies that have over 10 votes on TMDb.
	 * The default response will return 20 movies.
	 *
	 * @return the paginated results
	 */
	public PaginatedResult<MovieExtended> getTopRatedMovies() {
		return getTopRatedMovies(1, null);
	}

	/**
	 * The top rated movie method will let you retrieve the top rated movies that have over 10 votes on TMDb.
	 * The default response will return 20 movies.
	 *
	 * @param language It’s important to note that the language parameter acts as a filter for the title and poster field.
	 *                 This is to say, if you make a request for the German language,
	 *                 the original_title will always be returned but the title field will be empty if it hasn’t been translated.
	 *                 For posters, we’ll serve the German poster if available, otherwise default back to English.
	 *                 The expected value is a ISO 639-1 code.
	 * @return the paginated results
	 */
	public PaginatedResult<MovieExtended> getTopRatedMovies(String language) {
		return getTopRatedMovies(1, language);
	}

	/**
	 * The top rated movie method will let you retrieve the top rated movies that have over 10 votes on TMDb.
	 * The default response will return 20 movies.
	 *
	 * @param page This method will try likely have more than 20 results, the default number of items returned per page.
	 *             To iterate through use the page parameter.
	 * @return the paginated results
	 */
	public PaginatedResult<MovieExtended> getTopRatedMovies(int page) {
		return getTopRatedMovies(page, null);
	}

	/**
	 * The top rated movie method will let you retrieve the top rated movies that have over 10 votes on TMDb.
	 * The default response will return 20 movies.
	 *
	 * @param page     This method will try likely have more than 20 results, the default number of items returned per page.
	 *                 To iterate through use the page parameter.
	 * @param language It’s important to note that the language parameter acts as a filter for the title and poster field.
	 *                 This is to say, if you make a request for the German language,
	 *                 the original_title will always be returned but the title field will be empty if it hasn’t been translated.
	 *                 For posters, we’ll serve the German poster if available, otherwise default back to English.
	 *                 The expected value is a ISO 639-1 code.
	 * @return the paginated results
	 */
	public PaginatedResult<MovieExtended> getTopRatedMovies(int page, String language) {
		if (page < 1) {
			throw new IllegalArgumentException("page must be greater or equal to one");
		}

		if (language == null) {
			language = defaultLanguage;
		}
		LOG.debug("getTopRatedMovies: page={}, language={}", page, language);
		try {
			return serviceAPI.getTopRatedMovies(apiKey, page, language);
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	/**
	 * The popular movie method will let you retrieve the daily movie popularity list.
	 * This list is updated daily. The default response will return 20 movies.
	 *
	 * @return the paginated results
	 */
	public PaginatedResult<MovieExtended> getPopularMovies() {
		return getPopularMovies(1, null);
	}

	/**
	 * The popular movie method will let you retrieve the daily movie popularity list.
	 * This list is updated daily. The default response will return 20 movies.
	 *
	 * @param language It’s important to note that the language parameter acts as a filter for the title and poster field.
	 *                 This is to say, if you make a request for the German language,
	 *                 the original_title will always be returned but the title field will be empty if it hasn’t been translated.
	 *                 For posters, we’ll serve the German poster if available, otherwise default back to English.
	 *                 The expected value is a ISO 639-1 code.
	 * @return the paginated results
	 */
	public PaginatedResult<MovieExtended> getPopularMovies(String language) {
		return getPopularMovies(1, language);
	}

	/**
	 * The popular movie method will let you retrieve the daily movie popularity list.
	 * This list is updated daily. The default response will return 20 movies.
	 *
	 * @param page This method will try likely have more than 20 results, the default number of items returned per page.
	 *             To iterate through use the page parameter.
	 * @return the paginated results
	 */
	public PaginatedResult<MovieExtended> getPopularMovies(int page) {
		return getPopularMovies(page, null);
	}

	/**
	 * The popular movie method will let you retrieve the daily movie popularity list.
	 * This list is updated daily. The default response will return 20 movies.
	 *
	 * @param page     This method will try likely have more than 20 results, the default number of items returned per page.
	 *                 To iterate through use the page parameter.
	 * @param language It’s important to note that the language parameter acts as a filter for the title and poster field.
	 *                 This is to say, if you make a request for the German language,
	 *                 the original_title will always be returned but the title field will be empty if it hasn’t been translated.
	 *                 For posters, we’ll serve the German poster if available, otherwise default back to English.
	 *                 The expected value is a ISO 639-1 code.
	 * @return the paginated results
	 */
	public PaginatedResult<MovieExtended> getPopularMovies(int page, String language) {
		if (page < 1) {
			throw new IllegalArgumentException("page must be greater or equal to one");
		}

		if (language == null) {
			language = defaultLanguage;
		}
		LOG.debug("getPopularMovies: page={}, language={}", page, language);
		try {
			return serviceAPI.getPopularMovies(apiKey, page, language);
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	/**
	 * This method is used to retrieve all of the basic information about a movie collection.
	 *
	 * @param collectionId the id of the collection
	 * @return the collection info
	 * @throws de.dirkreske.media.scraper.themoviedb.exception.NotFoundException
	 *          if the collection id was not found
	 */
	public CollectionInfo getCollectionInfo(int collectionId) {
		return getCollectionInfo(collectionId, null);
	}

	/**
	 * This method is used to retrieve all of the basic information about a movie collection.
	 *
	 * @param collectionId the id of the collection
	 * @param language     the language
	 * @return the collection info
	 * @throws de.dirkreske.media.scraper.themoviedb.exception.NotFoundException
	 *          if the collection id was not found
	 */
	public CollectionInfo getCollectionInfo(int collectionId, String language) {
		if (language == null) {
			language = defaultLanguage;
		}

		LOG.debug("getCollectionInfo: collectionId={}, language={}", collectionId, language);
		try {
			return serviceAPI.getCollectionInfo(apiKey, collectionId, language);
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	/**
	 * Some elements of the API require some knowledge of the configuration data which can be found here.
	 * <p>
	 * The purpose of this is to try and keep the actual API responses as light as possible.
	 * That is, by not repeating a bunch of data like image URLs or sizes.
	 * </p>
	 *
	 * @return a configuration object
	 */
	public Configuration getConfiguration() {
		return serviceAPI.getConfiguration(apiKey);
	}

	/**
	 * This is a good starting point to start finding people on TMDb.
	 * The idea is to be a quick and light method so you can iterate through people quickly.
	 *
	 * @param search The query param is your search text.
	 * @return the paginated search results
	 */
	public PaginatedResult<PersonSimple> searchPerson(String search) {
		return searchPerson(search, 1);
	}

	/**
	 * This is a good starting point to start finding people on TMDb.
	 * The idea is to be a quick and light method so you can iterate through people quickly.
	 *
	 * @param search The query param is your search text.
	 * @param page   Some searches will have more than 20 results, the default number of items returned per page.
	 *               To iterate through use the page parameter.
	 * @return the paginated search results
	 */
	public PaginatedResult<PersonSimple> searchPerson(String search, int page) {
		return searchPerson(search, page, false);
	}

	/**
	 * This is a good starting point to start finding people on TMDb.
	 * The idea is to be a quick and light method so you can iterate through people quickly.
	 *
	 * @param search       The query param is your search text.
	 * @param page         Some searches will have more than 20 results, the default number of items returned per page.
	 *                     To iterate through use the page parameter.
	 * @param includeAdult You can toggle whether or not to include adult items in your search by using this parameter.
	 * @return the paginated search results
	 */
	public PaginatedResult<PersonSimple> searchPerson(String search, int page, boolean includeAdult) {
		if (page < 1) {
			throw new IllegalArgumentException("page must be greater or equal to one");
		}

		LOG.debug("searchPersons: search={}, page={}, includeAdult={}", new Object[]{search, page, includeAdult});
		try {
			return serviceAPI.searchPersons(apiKey, search, page, includeAdult);
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	/**
	 * The company info method will return the basic information about a production company on TMDb.
	 *
	 * @param companyId the companies id
	 * @return the company details
	 */
	public CompanyDetailed getCompany(int companyId) {
		LOG.debug("getCompany: companyId={}", companyId);
		try {
			return serviceAPI.getCompany(apiKey, companyId);
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	/**
	 * The company movies method will let you retrieve the movies associated with a company.
	 * These movies are returned in order of most recently released to oldest. The default response will return 20 movies per page.
	 *
	 * @param companyId the id of the company
	 * @return the searchMovies result
	 */
	public PaginatedResult<MovieExtended> getCompanyMovies(int companyId) {
		return getCompanyMovies(companyId, 1);
	}

	/**
	 * The company movies method will let you retrieve the movies associated with a company.
	 * These movies are returned in order of most recently released to oldest. The default response will return 20 movies per page.
	 *
	 * @param companyId the id of the company
	 * @param page      This method will try likely have more than 20 results, the default number of items returned per page.
	 *                  To iterate through use the page parameter.
	 * @return the searchMovies result
	 */
	public PaginatedResult<MovieExtended> getCompanyMovies(int companyId, int page) {
		return getCompanyMovies(companyId, page, null);
	}

	/**
	 * The company movies method will let you retrieve the movies associated with a company.
	 * These movies are returned in order of most recently released to oldest. The default response will return 20 movies per page.
	 *
	 * @param companyId the id of the company
	 * @param page      This method will try likely have more than 20 results, the default number of items returned per page.
	 *                  To iterate through use the page parameter.
	 * @param language  It’s important to note that the language parameter acts as a filter for the title and poster field.
	 *                  This is to say, if you make a request for the German language,
	 *                  the original_title will always be returned but the title field will be empty if it hasn’t been translated.
	 *                  For posters, we’ll serve the German poster if available, otherwise default back to English.
	 *                  The expected value is a ISO 639-1 code.
	 * @return the searchMovies result
	 */
	public PaginatedResult<MovieExtended> getCompanyMovies(int companyId, int page, String language) {
		if (page < 1) {
			throw new IllegalArgumentException("page must be greater or equal to one");
		}

		if (language == null) {
			language = defaultLanguage;
		}

		LOG.debug("getCompanyMovies: companyId={}, page={}", companyId, page);
		try {
			return serviceAPI.getCompanyMovies(apiKey, companyId, page, language);
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	/**
	 * This method is used to retrieve all of the basic person information.
	 * It will return the single highest rated profile image.
	 *
	 * @param personId the id of the person
	 * @return the person data
	 */
	public PersonDetailed getPersonInfo(int personId) {
		LOG.debug("getPersonInfo: personId={}", personId);
		try {
			return serviceAPI.getPerson(apiKey, personId);
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	/**
	 * This method is used to retrieve all of the cast & crew information for the person.
	 * It will return the single highest rated poster for each movie record.
	 *
	 * @param personId the persons id
	 * @param language The language parameter tries to localize the movie data we return.
	 *                 If the language being requested does not exist, the value will be left blank.
	 *                 The expected value is a ISO 639-1 code.
	 * @return the person credits
	 */
	public PersonCredits getPersonCredits(int personId, String language) {
		if (language == null) {
			language = defaultLanguage;
		}

		LOG.debug("getPersonCredits: personId={}", personId);
		try {
			return serviceAPI.getPersonCredits(apiKey, personId, language);
		} catch (ClientResponseFailure e) {
			throw mapClientResponseFailures(e);
		}
	}

	private RuntimeException mapClientResponseFailures(ClientResponseFailure failure) {
		switch (failure.getResponse().getResponseStatus()) {
			case NOT_FOUND:
				return new NotFoundException(failure);
			case UNAUTHORIZED:
				return new NotAuthorizedException(failure);
			default:
				return new RuntimeException(failure);
		}
	}

	/**
	 * Gets the api key used by this tmdb client.
	 *
	 * @return the api key
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * Sets the api key to be used by this tmdb client.
	 *
	 * @param apiKey the api key
	 */
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	/**
	 * Gets the default language used by this client if no language is set.
	 *
	 * @return the default language
	 */
	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	/**
	 * Sets the default language to be used by this tmdb client if no language is set.
	 *
	 * @param defaultLanguage the default language
	 */
	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

	/**
	 * Gets the current session id.
	 *
	 * @return the session id
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * Sets the current session id.
	 *
	 * @param sessionId the session id
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	private int getAccountId() {
		if (accountId == null) {
			accountId = getAccountInfo().getId();
		}
		return accountId;
	}
}
