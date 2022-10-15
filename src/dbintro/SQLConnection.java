package dbintro;

import java.sql.*;

public class SQLConnection {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public SQLConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection("jdbc:mysql://localhost/test_martinelli", "root", "");
    }

    public ResultSet executeStatement(String sql) throws SQLException {
        statement = connection.createStatement();

        return statement.executeQuery(sql);
    }

    public void executeInsert() throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO utenti VALUES(default, ?, ?, ?)");

        preparedStatement.setString(1, "Java");
        preparedStatement.setString(2, "Java");
        preparedStatement.setString(3, "java@java.jar");

        preparedStatement.executeUpdate();
    }
}
