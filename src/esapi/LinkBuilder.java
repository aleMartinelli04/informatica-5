package esapi;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LinkBuilder {
    private static final String URL = "https://jsonplaceholder.typicode.com/posts";

    private final Map<String, String> params;

    public LinkBuilder() {
        this.params = new HashMap<>();
    }

    public LinkBuilder fromUser(int userId) {
        params.put("userId", String.valueOf(userId));
        return this;
    }

    public LinkBuilder withId(int id) {
        params.put("id", String.valueOf(id));
        return this;
    }

    public LinkBuilder withTitle(String title) {
        params.put("title", title);
        return this;
    }

    public String build() {
        return URL + "?" + params.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
    }
}
