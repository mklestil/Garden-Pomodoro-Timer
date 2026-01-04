package at.tintildev.gardenpomodorotimer.repository.sqlite;

import at.tintildev.gardenpomodorotimer.domain.PomodoroSession;
import at.tintildev.gardenpomodorotimer.repository.SessionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SessionSQLiteRepository implements SessionRepository {

    private Connection connection;

    public SessionSQLiteRepository(){
        try {
            connection = implementationConnection();
            createTable();
        } catch (SQLException e) {
            throw new RuntimeException("Error initializing ImagesDAO: " + e.getMessage(), e);
        }
    }


    public Connection implementationConnection() throws SQLException {
        Connection con = SQLiteConnection.getConnection();
        return con;
    }


    public void createTable() {
        if (connection == null) {
            System.out.println("Error: No valid database connection!");
            return;
        }

        String sql = """
            CREATE TABLE IF NOT EXISTS pomodoro_sessions (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                duration INTEGER NOT NULL,
                plant_choice TEXT NOT NULL,
                timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
                tag TEXT NOT NULL
            );
            """;

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table 'pomodoro_sessions' checked/created.");
        } catch (SQLException e) {
            System.err.println("Error creating the table: " + e.getMessage());
        }
    }


    public void insert(PomodoroSession model) {
        if (connection == null) {
            System.out.println("Error: No valid database connection!");
            return;
        }

        String sql = "INSERT INTO pomodoro_sessions (duration, plant_choice, tag) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, model.getDuration());
            pstmt.setString(2, model.getPlantChoice());
            // timestamp = SQLite automatically sets the current time in UTC format (YYYY-MM-DD HH:MM:SS)
            pstmt.setString(3, model.getTag());
            pstmt.executeUpdate();
            System.out.println("Session saved: " + model.getDuration() + " min, plant: " + model.getPlantChoice());
        } catch (SQLException e) {
            System.err.println("Error by save the session: " + e.getMessage());
        }
    }


    public void update(PomodoroSession object) {

    }


    public void delete(PomodoroSession object) {

    }


    public PomodoroSession findById(int id) {
        return null;
    }


    public ArrayList<PomodoroSession> findAll() {
        if (connection == null) {
            System.out.println("Error: No valid database connection!");
            return null;
        }
        //TODO implement findAll method
        String sql = "SELECT * FROM pomodoro_sessions";
        ArrayList<PomodoroSession> sessions = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             var rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int duration = rs.getInt("duration");
                String plantChoice = rs.getString("plant_choice");
                String timestamp = rs.getString("timestamp");
                String tag = rs.getString("tag");
                sessions.add(new PomodoroSession(id, duration, plantChoice, timestamp, tag));
            }
            return sessions;
        } catch (SQLException e) {
            System.err.println("Error retrieving sessions: " + e.getMessage());
        }



        return sessions;
    }
}
