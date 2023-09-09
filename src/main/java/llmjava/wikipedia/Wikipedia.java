package llmjava.wikipedia;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import llmjava.wikipedia.response.*;

public class Wikipedia {

  String language = "en";
  RateLimiter rateLimiter;
  String userAgent = "wikipedia4j (https://github.com/llmjava/wikipedia4j)";
  Api api;

  public Wikipedia() {
    this("en");
  }

  public Wikipedia(String language) {
    this.language = language;
    this.rateLimiter = new RateLimiter();
    this.api = new Api(rateLimiter);
  }

  /**
   * Enable or disable rate limiting on requests to the Mediawiki servers.
   * If rate limiting is not enabled, under some circumstances (depending on load on Wikipedia, the number of requests you and other `wikipedia` users are making, and other factors), Wikipedia may return an HTTP timeout error.
   * Enabling rate limiting generally prevents that issue, but please note that HTTPTimeoutError still might be raised.
   * 
   * @param rate_limit (Boolean) - whether to enable rate limiting or not
   * @param min_wait (milliseconds) - if rate limiting is enabled, `min_wait` is a timedelta describing the minimum time to wait before requests.
   */
  public void set_rate_limiting(Boolean rate_limit, Long min_wait) {
    rateLimiter.RATE_LIMIT = rate_limit;
    if(!rate_limit) {
      rateLimiter.RATE_LIMIT_MIN_WAIT = null;
    } else {
      rateLimiter.RATE_LIMIT_MIN_WAIT = Duration.ofMillis(min_wait);
    }
    rateLimiter.RATE_LIMIT_LAST_CALL = null;
  }

  /**
   * Do a wikipedia geo search for `latitude` and `longitude` using HTTP API described in http://www.mediawiki.org/wiki/Extension:GeoData
   * @param latitude - latitude of center point
   * @param longitude - longitude of center point
   * @return 10 results in a radius of 10000 from the center point
   */
  public List<Document> geosearch(Double latitude, Double longitude) {
    return geosearch(latitude, longitude, null, 10, 10000);
  }

  /**
   * Do a wikipedia geo search for `latitude` and `longitude` using HTTP API described in http://www.mediawiki.org/wiki/Extension:GeoData
   * @param latitude - latitude of center point
   * @param longitude - longitude of center point
   * @param title - The title of an article to search for
   * @param results - the maximum number of results returned
   * @param radius - Search radius in meters. The value must be between 10 and 10000
   */
  public List<Document> geosearch(Double latitude, Double longitude, String title, int results, int radius) {
    Request request = new Request();
    request.setLanguage(this.language);
    request.setUserAgent(userAgent);
    request.addParam("list", "geosearch");
    request.addParam("gsradius", String.valueOf(radius));
    request.addParam("gscoord", latitude + "|" + longitude);
    request.addParam("gslimit", String.valueOf(results));

    if(title != null && !title.isEmpty())
      request.addParam("titles", title);

    GeoSearchResponse response = this.api.execute(request, new GeoSearchResponse.Parser());
    List<Document> docs = new ArrayList<>(response.getResults().size());
    for(GeoPage page: response.getResults()) {
      docs.add(new Document.GeoDocument(this.language, page.pageid, page.title, page.lat, page.lon, page.dist));
    }

    return docs;
  }

  /**
   * Get a Wikipedia search suggestion for `query`.
   * @param query search query
   * @return a string or null if no suggestion was found.
   */
  public List<Document> suggest(String query) {
    Request request = new Request(language, userAgent);
    request.addParam("list", "search");
    request.addParam("srinfo", "suggestion");
    request.addParam("srprop", "");
    request.addParam("srsearch", query);

    SuggestionResponse response = this.api.execute(request, new SuggestionResponse.Parser());
    return response.getDocs(this.language);
  }


  public List<Document> search(String term) throws Exception {
    Request request = new Request(language, userAgent);
    request.addParam("titles", term);
    request.addParam("prop", "extracts");
    request.addParam("explaintext", "true");

    Response response = this.api.execute(request, new Response.Parser());
    List<Document> docs = new ArrayList<>(response.getResults().size());
    for(Page page: response.getResults().values()) {
      docs.add(new Document(this.language, page.pageid, page.title, page.extract));
    }

    if (docs.isEmpty()) {
      throw new Exception("No Wikipedia page found for term \"" + term + "\"");
    }

    return docs;
  }

  /**
   * Do a Wikipedia search for `query`.
   * @param query search query
   * @param results the maxmimum number of results returned
   * @param suggestion if True, return results and suggestion (if any) in a tuple
   * @return list of matching documents
   * @throws Exception
   */
  public List<Document> search(String query, int results, boolean suggestion) throws Exception {
    Request request = new Request(language, userAgent);
    request.addParam("list", "search");
    request.addParam("srprop", "");
    request.addParam("srlimit", String.valueOf(results));
    request.addParam("srsearch", query);
    if(suggestion)
      request.addParam("srinfo", "suggestion");

    SuggestionResponse response = this.api.execute(request, new SuggestionResponse.Parser());
    List<Document> docs = new ArrayList<>(response.getResults().size());
    for(Page page: response.getResults()) {
      docs.add(new Document(this.language, page.pageid, page.title, page.extract));
    }

    return docs;
  }
}
