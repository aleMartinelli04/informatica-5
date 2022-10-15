package dbcars;

import java.sql.ResultSet;
import java.sql.SQLException;

public record Automobile(int id, String modello, String casaCostruttrice, String colore, int cilindrata, String alimentazione) {
    public static Automobile fromResultSet(ResultSet resultSet) throws SQLException {
        return new Automobile(
                resultSet.getInt("id"),
                resultSet.getString("modello"),
                resultSet.getString("casaCostruttrice"),
                resultSet.getString("colore"),
                resultSet.getInt("cilindrata"),
                resultSet.getString("alimentazione")
        );
    }

    public static String[] getColumnNames() {
        return new String[] {"id", "modello", "casa costruttrice", "colore", "cilindrata", "alimentazione"};
    }

    public Object[] getAsArray() {
        return new Object[] {id, modello, casaCostruttrice, colore, cilindrata, alimentazione};
    }
}
