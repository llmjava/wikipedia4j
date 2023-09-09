package llmjava.wikipedia;

import java.util.HashMap;
import java.util.Map;

public class Request {

    String baseUrl;
    Map<String, String> params = new HashMap<>();
    Map<String, String> headers = new HashMap<>();

    public Request() {
      baseUrl = "https://en.wikipedia.org/w/api.php";
      headers.put("content-type", "application/json;charset=UTF-8");
      headers.put("User-Agent", "wikipedia4j (https://github.com/llmjava/wikipedia4j)");
    }

    public void setLanguage(String language) {
        this.baseUrl = "https://" + language + ".wikipedia.org/w/api.php";
    }

    /**
     * Set User-Agent header, default to "wikipedia4j (https://github.com/llmjava/wikipedia4j)"
     * @param userAgent (String) - User-Agent header
     */
    public void setUserAgent(String userAgent) {
        headers.put("User-Agent", userAgent);
    }

    /**
     * Add a URL query parameter
     * @param key (String) - name of the query parameter
     * @param value (String) - value of the query parameter
     */
    public void addParam(String key, String value) {
        params.put(key, value);
    }

    /**
     * Add a header
     * @param key (String) - name of the header
     * @param value (String) - value of the header parameter
     */
    public void addHeader(String key, String value) {
        headers.put(key, value);
    }
}
