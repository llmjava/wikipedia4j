package llmjava.wikipedia;

import java.util.List;

import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertWithMessage;

public class WikipediaTest {
  public static void main(String[] args) throws Exception {
    Wikipedia wiki = new Wikipedia();
    System.out.println("============ Query ============");
    List<Document> results1 = wiki.search("apple");
    for(Document doc: results1) {
        System.out.println(doc);
    }

    System.out.println("============ Query ============");
    List<Document> results2 = wiki.geosearch(36.8112314, 3.0362203);
    System.out.println(results2);

    System.out.println("============ Query ============");
    List<Document>  results3 = wiki.suggest("apple");
    System.out.println(results3);

    System.out.println("============ Query ============");
    List<Document>  results4 = wiki.search("apple", 10, true);
    System.out.println(results4);
  }

  @Test
  public void searchShouldReturnResults() throws Exception {
    Wikipedia wiki = new Wikipedia();
    List<Document> results = wiki.search("apple");
    assertWithMessage("results not empty").that(results).isNotEmpty();
  }
}
