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

  public static class GeoDocument extends Document {
    Double lat;
    Double lon;
    Double dist;
    public GeoDocument(String language, String id, String title, Double lat, Double lon, Double dist) {
      super(language, id, title, null);
      this.lat = lat;
      this.lon = lon;
      this.dist = dist;
    }

    @Override public String toString() {
      return "Document[url: "+getUrl()+", title: "+getTitle()+", lat: "+lat+", lon: "+lon+", dist: "+dist+"]";
    }
  }
}
