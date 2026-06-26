import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseManager.java
 * Handles the JDBC database connection.
 *
 * Default configuration below is for MySQL.
 * Change URL, USER, PASSWORD according to your own database configuration.
 *
 * For PostgreSQL, the URL would look like:
 *   jdbc:postgresql://localhost:5432/game_project
 *
 * For SQL Server, the URL would look like:
 *   jdbc:sqlserver://localhost:1433;databaseName=game_project;encrypt=true;trustServerCertificate=true;
 */
public class DatabaseManager {

    // TODO: Change these values based on your database configuration
    private static final String URL = "jdbc:mysql://localhost:3306/game_project";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
