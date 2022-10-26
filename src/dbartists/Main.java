package dbartists;

import _hikaribase.Database;

import java.sql.PreparedStatement;

public class Main {
    public static void main(String[] args) {
        Database db = new Database("concerti", "root", "");

        db.useConnection(connection -> {
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS artisti(" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "nome VARCHAR(32), " +
                    "genere VARCHAR(32), " +
                    "inAttivita BOOLEAN, " +
                    "numeroAlbum INT)");

            statement.execute();
        });

        new GuiArtists(db).setVisible(true);
    }
}
