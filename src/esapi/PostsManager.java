package esapi;

import kong.unirest.HttpResponse;
import kong.unirest.JsonObjectMapper;
import kong.unirest.Unirest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class PostsManager {
    private final Set<Post> loadedPosts;

    public PostsManager() {
        this.loadedPosts = new HashSet<>();
    }

    public Set<Post> getLoadedPosts() {
        return loadedPosts;
    }

    public void loadPosts(LinkBuilder builder) {
        HttpResponse<String> httpResponse = Unirest.get(builder.build()).asString();

        JsonObjectMapper mapper = new JsonObjectMapper();

        Post[] loaded = mapper.readValue(httpResponse.getBody(), Post[].class);

        loadedPosts.addAll(Arrays.asList(loaded));
    }

    public Post getById(int id) {
        return loadedPosts.stream()
                .filter(post -> post.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void deletePost(int id) {
        loadedPosts.removeIf(post -> post.getId() == id);
    }

    public void deleteAllPosts() {
        loadedPosts.clear();
    }
}
