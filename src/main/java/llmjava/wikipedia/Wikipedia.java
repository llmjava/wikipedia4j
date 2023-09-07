package llmjava.wikipedia;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.*;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Wikipedia {


  public List<Document> search(String term) throws Exception {
    // String term = this.options.getTerm();

    List<Document> docs = this.fetchDocForTerm(term);

    if (docs == null) {
      throw new Exception("No Wikipedia page found for term \"" + term + "\"");
    }

    return docs;
  }

  private List<Document> fetchDocForTerm(String term) throws Exception {
    Map<String, String> params = Map.of(
        "action", "query",
        "format", "json",
        "titles", term,
        "prop", "extracts",
        "explaintext", "true"
        );
    String baseUrl = "https://en.wikipedia.org/w/api.php";
    URL url = buildUrl(baseUrl, params);
    
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestProperty("content-type", "application/json;charset=UTF-8");

    String jsonResult = read(connection);
    Response response = Response.parse(jsonResult);
    List<Document> documents = new ArrayList<>(response.query.pages.size());
    for(Response.Page page: response.query.pages.values()) {
      documents.add(new Document(page.pageid, page.title, page.extract));
    }

    return documents;
  }

  private URL buildUrl(String baseUrl, Map<String, String> params) throws IOException {
    StringBuffer urlBuilder = new StringBuffer("https://en.wikipedia.org/w/api.php");
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
