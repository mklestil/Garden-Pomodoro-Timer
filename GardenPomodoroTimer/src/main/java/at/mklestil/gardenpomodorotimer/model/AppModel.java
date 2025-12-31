package at.mklestil.gardenpomodorotimer.model;

import java.util.ArrayList;

/**
 * AppModel local data
 */
public class AppModel {
    private String selectedPlant = "/images/start/start.png";
    private int time = 25;
    private String tag = "tag auswählen";
    private String currentLanguage = "de";
    private static AppModel instance;
    private String backgroundColor = "#55C57A";
    private ArrayList<String> tagsList = new ArrayList<>();

    private int appWidth = 280;
    private int appHight = 420;

    private AppModel() {

    }

    public static AppModel getInstance() {
        if (instance == null) {
            instance = new AppModel();
        }
        return instance;
    }

    public String getSelectedPlant() {
        return selectedPlant;
    }

    public void setSelectedPlant(String selectedPlant) {
        this.selectedPlant = selectedPlant;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getCurrentLanguage() {
        return currentLanguage;
    }

    public void setCurrentLanguage(String language) {
        this.currentLanguage = language;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getAppWidth() {
        return appWidth;
    }

    public void setAppWidth(int appWidth) {
        this.appWidth = appWidth;
    }

    public int getAppHight() {
        return appHight;
    }

    public void setAppHight(int appHight) {
        this.appHight = appHight;
    }

    public ArrayList<String> getTagsList() {
        return tagsList;
    }

    public void setTagsList(ArrayList<String> tagsList) {
        this.tagsList = tagsList;
    }
}
