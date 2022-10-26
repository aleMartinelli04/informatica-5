package jsonother;

import java.io.IOException;
import java.nio.file.Path;

public class MainJsonOther {
    public static void main(String[] args) throws IOException {
        ArtistsManager artistsManager = new ArtistsManager(Path.of("src/jsonother/artists.json"));

        new JsonOtherGui(artistsManager).setVisible(true);
    }
}
