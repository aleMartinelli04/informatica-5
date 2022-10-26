package hikaribase;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.pool.HikariPool;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private final HikariPool pool;

    public Database(String dbName, String username, String password) {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://localhost:3306/" + dbName + "?useSSL=false&serverTimezone=UTC");
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(10);
        config.setPoolName("martinelli");

        this.pool = new HikariPool(config);
    }

    public void useConnection(SQLConsumer<Connection> consumer) {
        try (Connection connection = pool.getConnection()) {
            consumer.accept(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
