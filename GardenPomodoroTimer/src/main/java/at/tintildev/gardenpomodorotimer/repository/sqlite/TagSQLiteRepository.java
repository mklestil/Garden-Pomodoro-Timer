package at.tintildev.gardenpomodorotimer.repository.sqlite;

import at.tintildev.gardenpomodorotimer.domain.Tags;
import at.tintildev.gardenpomodorotimer.repository.TagRepository;
import at.tintildev.gardenpomodorotimer.state.ApplicationState;

import java.sql.*;
import java.util.ArrayList;

public class TagSQLiteRepository implements TagRepository {
    private Connection connection;

    public TagSQLiteRepository()  {
        try {
            connection = implementationConnection();
            createTable();
        } catch (SQLException e) {
            throw new RuntimeException("Error initializing: " + e.getMessage(), e);
        }
    }


    public Connection implementationConnection() throws SQLException {
        Connection con = SQLiteConnection.getConnection();
        return con;
    }


    public void createTable() {
        String sql = """
        CREATE TABLE IF NOT EXISTS tags (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT UNIQUE NOT NULL
        );
        """;
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error creating the table: " + e.getMessage());
        }
    }


    public void insert(ApplicationState model) {
        String tag = model.getTag();
        if (connection == null) {
            System.out.println("Error: No valid database connection!");
            return;
        }

        String sql = "INSERT INTO tags (name) VALUES (?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, tag);
            pstmt.executeUpdate();
            System.out.println("tag saved: " + tag);
        } catch (SQLException e) {
            System.err.println("Error by save the tag: " + e.getMessage());
        }
    }

    public void update(String tag, String newTag) {
        if (connection == null) {
            System.out.println("Error: No valid database connection!");
            return;
        }

        String sql = "UPDATE tags SET name = ? WHERE name = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, newTag);
            pstmt.setString(2, tag);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Tag updated from " + tag + " to " + newTag);
            } else {
                System.out.println("No tag found with the name: " + tag);
            }
        } catch (SQLException e) {
            System.err.println("Error updating the tag: " + e.getMessage());
        }


    }

    @Override
    public void delete(ApplicationState model) {

    }

    @Override
    public Tags findById(int id) {
        return null;
    }


    public ArrayList findAll() {
        ArrayList<String> tags = new ArrayList<>();
        if (connection == null) {
            System.out.println("Error: No valid database connection!");
            return tags;
        }else{
            String sql = "SELECT name FROM tags";
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    String tagName = rs.getString("name");
                    tags.add(tagName);
                }
            } catch (SQLException e) {
                System.err.println("Error retrieving tags: " + e.getMessage());
            }
        }
        return tags;
    }


}
