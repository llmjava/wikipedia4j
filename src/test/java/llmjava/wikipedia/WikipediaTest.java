package llmjava.wikipedia;

import java.util.List;

public class WikipediaTest {
    public static void main(String[] args) throws Exception {
        Wikipedia wiki = new Wikipedia();
        List<Document> results = wiki.search("apple");
        for(Document doc: results) {
            System.out.println(doc);
        }
    }
}
