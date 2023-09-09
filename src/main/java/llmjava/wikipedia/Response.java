package llmjava.wikipedia;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class Response {
    String batchcomplete;

    Query query;

    static class Query {
        List<GeoPage> geosearch;
        List<Normalized> normalized;
        Map<String, Page> pages;

        @Override public String toString() {
            return "Query[normalized: "+normalized+", pages: "+pages+", geosearch: "+geosearch+"]";
        }
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

        @Override public String toString() {
            return "Page[pageid: "+pageid+", ns: "+ns+", title: "+title+", extract: "+extract.substring(0, 20)+"]";
        }
    }

    static class GeoPage {
        String pageid;
        Integer ns;
        String title;
        Double lat;
        Double lon;
        Double dist;
        String primary;

        @Override public String toString() {
            return "Page[pageid: "+pageid+", ns: "+ns+", title: "+title+", lat: "+lat+", lon: "+lon+", dist: "+dist+", primary: "+primary+"]";
        }
    }

    public static Response parse(String jsonString) {
        Response response = new Gson().fromJson(jsonString, Response.class);
        return response;
    }

    @Override public String toString() {
        return "Response [batchcomplete: "+batchcomplete+", query: "+query+"]";
    }
}
