package llmjava.wikipedia.response;

import java.util.List;

import com.google.gson.Gson;

public class GeoSearchResponse {
    String batchcomplete;

    Query query;

    public static class Query {
        List<GeoPage> geosearch;
        List<Normalized> normalized;
    }

    public static class Normalized {
        String from;
        String to;
    }

    public List<GeoPage> getResults() {
        return query.geosearch;
    }

    public static class Parser implements ResponseParser<GeoSearchResponse> {
        @Override public GeoSearchResponse parse(String jsonString) {
            GeoSearchResponse response = new Gson().fromJson(jsonString, GeoSearchResponse.class);
            return response;
        }
    }

    @Override public String toString() {
        return "GeoSearchResponse [batchcomplete: "+batchcomplete+", query: "+query+"]";
    }
}
