package dbcars;

import java.sql.*;

public class SQLConnection {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public SQLConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection("jdbc:mysql://localhost/concessionario", "root", "");
    }

    public ResultSet getContent() throws SQLException {
        statement = connection.createStatement();

        return statement.executeQuery("SELECT * from automobili");
    }

    public void insert(Automobile automobile) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO automobili VALUES(default, ?, ?, ?, ?, ?)");

        preparedStatement.setString(1, automobile.modello());
        preparedStatement.setString(2, automobile.casaCostruttrice());
        preparedStatement.setString(3, automobile.colore());
        preparedStatement.setInt(4, automobile.cilindrata());
        preparedStatement.setString(5, automobile.alimentazione());

        preparedStatement.executeUpdate();

        preparedStatement.close();
    }
}
