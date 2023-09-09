package llmjava.wikipedia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class Api {

  RateLimiter rateLimiter;

  public Api(RateLimiter rateLimiter) {
    this.rateLimiter = rateLimiter;
  }

  /**
   * Make a request to the Wikipedia API using the given search parameters.
   * 
   * @param request (Request) - request to wikipedia.org
   * @return a parsed dict of the JSON response.
   */
  Response execute(Request request) {
    
    request.addParam("format", "json");

    if(!request.params.containsKey("action"))
      request.addParam("action", "query");

    rateLimiter.before();

    Response response = null;
    try {
      URL url = buildUrl(request.baseUrl, request.params);
      
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      for(Map.Entry<String, String> pair: request.headers.entrySet()) {
        connection.setRequestProperty(pair.getKey(), pair.getValue());
      }

      String jsonResult = read(connection);
      response = Response.parse(jsonResult);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    rateLimiter.after();

    return response;
  }

  private URL buildUrl(String baseUrl, Map<String, String> params) throws IOException {
    StringBuffer urlBuilder = new StringBuffer(baseUrl);
    urlBuilder.append( "?" );
    for(Map.Entry<String, String> entry: params.entrySet()) {
        urlBuilder.append( entry.getKey() );
        urlBuilder.append( "=" );
        urlBuilder.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        urlBuilder.append("&");
    }
    String urlString = urlBuilder.toString();
    if(urlString.endsWith("&")) {
        urlString = urlString.substring(0, urlString.length() - 1);
    }
    return new URL(urlString);
  }

  private String read(HttpURLConnection connection) throws IOException {
      final StringBuilder response = new StringBuilder();
      BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String inputLine;
      while ((inputLine = reader.readLine()) != null) {
          response.append(inputLine);
      }
      reader.close();
      return response.toString();
  }
}
