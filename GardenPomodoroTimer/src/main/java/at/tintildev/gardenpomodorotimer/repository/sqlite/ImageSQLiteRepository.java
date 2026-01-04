package at.tintildev.gardenpomodorotimer.repository.sqlite;

import at.tintildev.gardenpomodorotimer.repository.ImageRepository;
import at.tintildev.gardenpomodorotimer.state.ApplicationState;
import javafx.scene.image.Image;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

public class ImageSQLiteRepository implements ImageRepository {

    private final Connection connection;

    public ImageSQLiteRepository(){
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


    public void save(ApplicationState model) {
        String sql = "INSERT INTO images (path) VALUES('" + model.getSelectedPlant() + "')";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Bildpfad eingefügt: " + model.getSelectedPlant());
        } catch (SQLException e) {
            System.out.println("Fehler beim Einfügen des Bildpfads: " + e.getMessage());
        }
    }


    public void update(ApplicationState object) {

    }


    public void delete(ApplicationState object) {

    }


    public Object findById(int id) {
        return null;
    }


    public ArrayList findAll() {
        //TODO implement findAll method
        return null;
    }

    @Override
    public Optional<Image> findById(long id) {
        return Optional.empty();
    }


}
