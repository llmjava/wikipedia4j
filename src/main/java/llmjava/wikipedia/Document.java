package llmjava.wikipedia;

public class Document {

  private String language;
  private String id;
  private String title;
  private String text;

  public Document(String language, String id, String title, String text) {
    this.id = id;
    this.text = text;
    this.title = title;
    this.language = language;
  }

  public String getId() {
      return id;
  }

  public String getUrl() {
    return "https://" + language + ".wikipedia.org/?curid=" + id;
  }

  public String getText() {
    return this.text;
  }

  public String getTitle() {
    return this.title;
  }

  @Override public String toString() {
    return "Document[url: "+getUrl()+", title: "+getTitle()+", text: "+getText().substring(0, 50)+"...]";
  }
}
