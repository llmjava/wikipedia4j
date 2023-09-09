package llmjava.wikipedia.response;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import llmjava.wikipedia.Document;

public class SuggestionResponse {
    String batchcomplete;

    @SerializedName("continue") Continue continueObj;
    Query query;

    public static class Query {
        SearchInfo searchinfo;
        List<Page> search;

        @Override public String toString() {
            return "Query[searchinfo: "+searchinfo+", search: "+search+"]";
        }
    }

    public static class Continue {
        int sroffset;
        @SerializedName("continue") String continueField;
    }

    public static class SearchInfo {
        String suggestion;
        String suggestionsnippet;
    }

    public List<Page> getResults() {
        return query.search;
    }

    public List<Document> getDocs(String language) {
        List<Document> docs = new ArrayList<>(getResults().size());
        for(Page page: getResults()) {
            docs.add(new Document(language, page.pageid, page.title));
        }
        return docs;
    }

    public static class Parser implements ResponseParser<SuggestionResponse> {
        @Override public SuggestionResponse parse(String jsonString) {
            SuggestionResponse response = new Gson().fromJson(jsonString, SuggestionResponse.class);
            return response;
        }
    }

    @Override public String toString() {
        return "Response [batchcomplete: "+batchcomplete+", query: "+query+"]";
    }
}
