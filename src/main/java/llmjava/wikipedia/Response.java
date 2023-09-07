package llmjava.wikipedia;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class Response {
    String batchcomplete;

    Query query;

    static class Query {
        List<Normalized> normalized;
        Map<String, Page> pages;
    }

    static class Normalized {
        String from;
        String to;
    }

    static class Page {
        String pageid;
        Integer ns;
        String title;
        String extract;
    }

    public static Response parse(String jsonString) {
        Response response = new Gson().fromJson(jsonString, Response.class);
        return response;
    }
}
