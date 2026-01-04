package at.tintildev.gardenpomodorotimer.repository;

import at.tintildev.gardenpomodorotimer.domain.PomodoroSession;
import at.tintildev.gardenpomodorotimer.domain.Tags;
import at.tintildev.gardenpomodorotimer.state.ApplicationState;
import at.tintildev.gardenpomodorotimer.repository.sqlite.SQLiteConnection;

import java.sql.*;
import java.util.ArrayList;

public interface TagRepository  {


    public abstract void insert(ApplicationState model);
    public abstract void update(String tag, String newTag);
    public abstract void delete(ApplicationState model);
    public abstract Tags findById(int id);
    public ArrayList findAll();


}
