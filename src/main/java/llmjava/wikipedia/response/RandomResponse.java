package llmjava.wikipedia.response;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import llmjava.wikipedia.Document;

public class RandomResponse {
    String batchcomplete;

    @SerializedName("continue") Continue continueObj;
    Query query;

    public static class Query {
        List<Page> random;

        @Override public String toString() {
            return "Query[random: "+random+"]";
        }
    }

    public static class Continue {
        String rncontinue;
        @SerializedName("continue") String continueField;
    }

    public static class SearchInfo {
        String suggestion;
        String suggestionsnippet;
    }

    public List<Page> getResults() {
        return query.random;
    }

    public List<Document> getDocs(String language) {
        List<Document> docs = new ArrayList<>(getResults().size());
        for(Page page: getResults()) {
            docs.add(new Document(language, page.pageid, page.title));
        }
        return docs;
    }

    public static class Parser implements ResponseParser<RandomResponse> {
        @Override public RandomResponse parse(String jsonString) {
            RandomResponse response = new Gson().fromJson(jsonString, RandomResponse.class);
            return response;
        }
    }

    @Override public String toString() {
        return "Response [batchcomplete: "+batchcomplete+", query: "+query+"]";
    }
}
