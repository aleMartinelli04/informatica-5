package dbhouses;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SQLConnection sqlConnection = new SQLConnection("jdbc:mysql://localhost/houses_db", "root", "");

        new HousesGui(sqlConnection).setVisible(true);
    }
}
