package at.tintildev.gardenpomodorotimer.repository;

import at.tintildev.gardenpomodorotimer.domain.PomodoroSession;
import at.tintildev.gardenpomodorotimer.repository.sqlite.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public interface SessionRepository {

    public abstract void insert(PomodoroSession pomodoroSession);
    public abstract void update(PomodoroSession object);
    public abstract void delete(PomodoroSession object);
    public abstract PomodoroSession findById(int id);
    public abstract ArrayList<PomodoroSession> findAll();
}
