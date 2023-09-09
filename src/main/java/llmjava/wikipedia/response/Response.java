package llmjava.wikipedia.response;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class Response {
    String batchcomplete;

    Query query;

    public static class Query {
        List<Normalized> normalized;
        Map<String, Page> pages;

        @Override public String toString() {
            return "Query[normalized: "+normalized+", pages: "+pages+"]";
        }
    }

    public static class Normalized {
        String from;
        String to;
    }

    public Map<String, Page> getResults() {
        return query.pages;
    }

    public static class Parser implements ResponseParser<Response> {
        @Override public Response parse(String jsonString) {
            Response response = new Gson().fromJson(jsonString, Response.class);
            return response;
        }
    }

    @Override public String toString() {
        return "Response [batchcomplete: "+batchcomplete+", query: "+query+"]";
    }
}
