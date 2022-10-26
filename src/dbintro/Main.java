package dbintro;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SQLConnection sqlConnection = new SQLConnection();

        ResultSet resultSet = sqlConnection.executeStatement("SELECT * from utenti");

        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String nome = resultSet.getString("nome");
            String cognome = resultSet.getString("cognome");
            String mail = resultSet.getString("email");

            System.out.println(id + " " + nome + " " + cognome + " " + mail);
        }

        sqlConnection.executeInsert();
    }
}
