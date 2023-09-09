package llmjava.wikipedia.response;

public class Page {
    public String pageid;
    public Integer ns;
    public String title;
    public String extract;

    @Override public String toString() {
        return "Page[pageid: "+pageid+", ns: "+ns+", title: "+title+", extract: "+extract.substring(0, 20)+"]";
    }
}
