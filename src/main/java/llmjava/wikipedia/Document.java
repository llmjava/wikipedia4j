package llmjava.wikipedia;

public class Document {

  private String language;
  private String id;
  private String title;
  private String text;
  private Double lat = -1.0;
  private Double lon = -1.0;
  private Double dist = -1.0;

  public Document(String language, String id, String title) {
    this.language = language;
    this.id = id;
    this.title = title;
  }

  public Document(String language, String id, String title, Double lat, Double lon, Double dist) {
    this(language, id, title);
    this.lat = lat;
    this.lon = lon;
    this.dist = dist;
  }

  public Document(String language, String id, String title, String text) {
    this(language, id, title);
    this.text = text;
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
    String str = "Document[url: "+getUrl()+", title: "+getTitle();
    if(getText() != null) {
      str += ", text: "+getText().substring(0, 50)+"...";
    }
    if(lat != -1) {
      str += ", lat: " + lat;
    }
    if(lon != -1) {
      str += ", lon: " + lon;
    }
    if(dist != -1) {
      str += ", dist: " + dist;
    }
    str += "]";
    return str;
  }
}
