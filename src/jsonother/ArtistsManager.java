package jsonother;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArtistsManager {
    private final List<Artist> artists;

    public ArtistsManager(Path path) throws IOException {
        this.artists = new ArrayList<>();

        byte[] jsonData = Files.readAllBytes(path);

        ObjectMapper objectMapper = new ObjectMapper();

        Artist[] loaded = objectMapper.readValue(jsonData, Artist[].class);

        artists.addAll(Arrays.asList(loaded));
    }

    public List<Artist> getArtists() {
        return artists;
    }
}
