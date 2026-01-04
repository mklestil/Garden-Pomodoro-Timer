package at.tintildev.gardenpomodorotimer.domain;

/**
 * Model for PomodoroSessions, a central data model for Pomodoro sessions
 */
public class PomodoroSession {
    private static int ID_COUNTER = 0;
    private int id;
    private int duration;
    private String plantChoice;
    private String timestamp;
    private String TagChosen;

    public PomodoroSession(int id, int duration, String plantChoice, String timestamp, String tag) {
        this.id = id;
        this.duration = duration;
        this.plantChoice = plantChoice;
        this.timestamp = timestamp;
        this.TagChosen = tag;
    }

    public PomodoroSession(int duration, String plantChoice, String timestamp, String tag) {
        this(ID_COUNTER, duration, plantChoice, timestamp, tag);
        ID_COUNTER = +1; // Default ID to 0 for new sessions
    }

    public int getId() { return id; }
    public int getDuration() { return duration; }
    public String getPlantChoice() { return plantChoice; }
    public String getTimestamp() { return timestamp; }

    public String getTag() {
        return TagChosen;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Duration: " + duration + "min, Plant: " + plantChoice + ", Timestamp: " + timestamp;
    }
}