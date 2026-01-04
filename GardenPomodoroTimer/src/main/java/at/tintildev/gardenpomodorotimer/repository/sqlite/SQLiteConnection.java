package at.tintildev.gardenpomodorotimer.repository.sqlite;

import java.sql.*;

/**
 * This class is to manage the connection to the sqlite db.
 */
public class SQLiteConnection {

    private static final String url = "jdbc:sqlite:mydatabase.db";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        // SQLite connection string
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url);
        }
        return connection;
    }
}
