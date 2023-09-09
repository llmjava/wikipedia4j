package llmjava.wikipedia.response;

public class GeoPage {

    public String pageid;
    public Integer ns;
    public String title;
    public Double lat;
    public Double lon;
    public Double dist;
    public String primary;

    @Override public String toString() {
        return "Page[pageid: "+pageid+", ns: "+ns+", title: "+title+", lat: "+lat+", lon: "+lon+", dist: "+dist+", primary: "+primary+"]";
    }
}
