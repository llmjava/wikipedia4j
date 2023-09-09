package llmjava.wikipedia.response;

public interface ResponseParser<T> {
    T parse(String jsoString);
}
