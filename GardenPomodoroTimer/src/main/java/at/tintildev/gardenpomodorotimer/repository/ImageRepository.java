package at.tintildev.gardenpomodorotimer.repository;

import at.tintildev.gardenpomodorotimer.repository.sqlite.SQLiteConnection;
import at.tintildev.gardenpomodorotimer.state.ApplicationState;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ImageRepository implements Repository<ApplicationState> {

    private final Connection connection;

    public ImageRepository(){
        try {
            connection = implementationConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Error initializing ImagesDAO: " + e.getMessage(), e);
        }
    }
    @Override
    public Connection implementationConnection() throws SQLException {
        Connection con = SQLiteConnection.getConnection();
        return con;
    }

    @Override
    public void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS images (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    path TEXT NOT NULL
                );
                """;
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            //System.out.println("Tabelle 'images' erstellt (falls nicht vorhanden).");
        } catch (SQLException e) {
            System.out.println("Error creating the table: " + e.getMessage());
        }
    }

    @Override
    public void insert(ApplicationState model) {
        String sql = "INSERT INTO images (path) VALUES('" + model.getSelectedPlant() + "')";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Bildpfad eingefügt: " + model.getSelectedPlant());
        } catch (SQLException e) {
            System.out.println("Fehler beim Einfügen des Bildpfads: " + e.getMessage());
        }
    }

    @Override
    public void update(ApplicationState object) {

    }

    @Override
    public void delete(ApplicationState object) {

    }

    @Override
    public Object findById(int id) {
        return null;
    }

    @Override
    public ArrayList findAll() {
        //TODO implement findAll method
        return null;
    }


}
