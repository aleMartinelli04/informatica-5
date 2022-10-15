package dbhouses;

import java.sql.*;

public class SQLConnection {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public SQLConnection(String url, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(url, user, password);
    }

    public ResultSet executeStatement(String sql) throws SQLException {
        Statement statement = connection.createStatement();

        statement.executeQuery("INSERT INTO houses VALUES (default, ?, ?, ?)");

        return statement.executeQuery(sql);
    }
}
